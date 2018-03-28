package org.ulitzky.service.extendable;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.service.ICodekataService;
import org.ulitzky.service.effective.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * Created by lulitzky on 25.03.18.
 */
@Slf4j
public class ExtendableCodekataService implements ICodekataService {
    // Total number of results 18914
    // Total running time 353462 ms
    public  void findSolution(final String fileName) {
        try {
            Path inputFile = Paths.get(fileName);

            List<IProcessable> inputData = Files.lines(inputFile, Charset.defaultCharset()).parallel().map(s -> new Concatenation6String(s)).collect(Collectors.toList());
            ConcurrentSkipListSet<IProcessable> processingData = new ConcurrentSkipListSet<>();
            processingData.addAll(inputData);
            List<String> results = processInput(processingData);
            for (String result : results) {
                System.out.println(result);
            }
            log.info("Total number of results " + results.size());


        } catch (IOException e) {
            log.error("Cannot open file {}", fileName);
        }
    }

    List<String> processInput(final ConcurrentSkipListSet<IProcessable> processingData) {
        log.info("Processing input");
       List<IProcessingResult> results =
               processingData.stream()
                       .map(input -> input.checkRules(processingData))
                       .filter(Optional::isPresent)
                       .map(Optional::get)
                       .collect(Collectors.toList());

       return results.parallelStream().map(data -> data.toString()).collect(Collectors.toList());
    }
}
