package org.ulitzky.service.readable;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.service.ICodekataService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lulitzky on 25.03.18.
 */
@Slf4j
public class ReadableCodekataService implements ICodekataService {

//    Total number of results 18914
//    Total running time 183633 ms

    public  void findSolution(final String fileName) {
        try {
            Path inputFile = Paths.get(fileName);
            List<String> inWords = Files.readAllLines(inputFile, Charset.defaultCharset());
            List<String> resultList = processInputWords(inWords);

            for (String result : resultList) {
                System.out.println(result);
            }
            log.info("Total number of results " +resultList.size());

        } catch (IOException e) {
            log.error("Cannot open file {}", fileName);
        }
    }

     List<String> processInputWords(final List<String> inWords) {
        List<String> result = new LinkedList<>();

        List<String> candidateWords = inWords.stream().filter( s -> s.length() == 6).collect(Collectors.toList());

        for(String candidate : candidateWords) {
            Optional<String> candidateResult = checkCandidate(candidate, inWords);
            if (candidateResult.isPresent()) {
                result.add(candidateResult.get());
            }
        }
        return result;

    }

    private Optional<String> checkCandidate(final String candidate, final List<String> inWords) {
        for (int i=0; i < candidate.length(); i++) {
            String prefix = candidate.substring(0, i);
            String suffix = candidate.substring(i);

            if (inWords.contains(prefix) && inWords.contains(suffix)) {
                String resultString = buildResultString(prefix, suffix, candidate);
                return Optional.of(resultString);
            }
        }
        return Optional.empty();
    }


}
