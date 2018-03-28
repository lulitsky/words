package org.ulitzky.service.effective;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.service.ICodekataService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * Created by lulitzky on 25.03.18.
 */
@Slf4j
public class EffectiveCodekataService implements ICodekataService {

//    Total number of results 18914
//    Total running time 305 ms

    public  void findSolution(final String fileName) {
        try {
            Path inputFile = Paths.get(fileName);
            Map<Integer, ConcurrentSkipListSet<String>> processingData = new TreeMap<>();
            for(int i=1; i <7; i++) {
                processingData.put(i, new ConcurrentSkipListSet<>());
            }
            Files.lines(inputFile, Charset.defaultCharset()).parallel().filter(s -> s.length() < 7).forEach(s -> processingData.get(s.length()).add(s));
            Set<String> resultList = processInputWords(processingData);
            for (String result : resultList) {
                System.out.println(result);
            }
            log.info("Total number of results " +resultList.size());


        } catch (IOException e) {
            log.error("Cannot open file {}", fileName);
        }
    }

    Set<String> processInputWords(final Map<Integer, ConcurrentSkipListSet<String>> processingData) {
        Set<String> result = new ConcurrentSkipListSet<>();
        ConcurrentSkipListSet<String> candidates = processingData.get(6);
        for(int i=1; i<6; i++) {
            result.addAll(findBreakdowns(candidates, i, processingData.get(i), processingData.get(6-i)));
        }
        return result;
    }

    private Collection<String> findBreakdowns(final ConcurrentSkipListSet<String> candidates, final int prefixLength, final ConcurrentSkipListSet<String> prefixSet, final ConcurrentSkipListSet<String> suffixSet) {

        if (candidates != null) {
            List<ResultData> resultDataSet =
                    candidates.parallelStream().filter(s -> checkBreakdown(s, prefixLength, prefixSet, suffixSet)).map(s -> new ResultData(s, prefixLength)).collect(Collectors.toList());

            candidates.removeAll(resultDataSet.stream().map(data -> data.getResult()).collect(Collectors.toList()));

            return resultDataSet.stream().map(data -> buildResultString(data)).collect(Collectors.toList());
        } else {
            return Collections.<String>emptyList();
        }

    }

    private boolean checkBreakdown(final String candidate, final int prefixLength, final Set<String> prefixes, final Set<String> suffixes) {
        return prefixes.contains(candidate.substring(0, prefixLength)) && suffixes.contains(candidate.substring(prefixLength));
    }

    String buildResultString(final ResultData data) {
        return buildResultString(data.getPrefix(), data.getSuffix(), data.getResult());
    }


}
