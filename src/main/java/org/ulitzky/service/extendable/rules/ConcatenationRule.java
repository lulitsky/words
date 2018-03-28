package org.ulitzky.service.extendable.rules;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.service.extendable.AbstractProcessableString;
import org.ulitzky.service.extendable.Concatenation6String;
import org.ulitzky.service.extendable.IProcessingResult;
import org.ulitzky.service.extendable.ResultData;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by lulitzky on 26.03.18.
 */
@Slf4j
public class ConcatenationRule implements IRule<AbstractProcessableString> {
    @Override
    public Optional<IProcessingResult> validate(final AbstractProcessableString processableObject, final Object... args)  {
        ConcurrentSkipListSet<Concatenation6String> inputWords = getInputWords(args);
        String candidate = processableObject.getValue();
        Map<Integer, ConcurrentSkipListSet<String>> processingData = initProcessingData(candidate, inputWords);

        for(int i=1; i < candidate.length(); i++) {
            if (checkBreakdown(candidate, i, processingData.get(i), processingData.get(candidate.length() -i))) {
                return Optional.of(new ResultData(candidate, i));
            }
        }

        return Optional.empty();
    }

    private Map<Integer, ConcurrentSkipListSet<String>> initProcessingData(final String candidate, final ConcurrentSkipListSet<Concatenation6String> inputWords) {
        Map<Integer, ConcurrentSkipListSet<String>> processingData =  new TreeMap<>();
        for(int i=1; i <candidate.length(); i++) {
            processingData.put(i, new ConcurrentSkipListSet<>());
        }
        for (Concatenation6String input : inputWords) {
            String word = input.getValue();
            if (word.length() < candidate.length()) {
                processingData.get(word.length()).add(word);
            }
        }
        return processingData;
    }

    private static boolean  checkBreakdown(final String candidate, final int prefixLength, final Set<String> prefixes, final Set<String> suffixes) {
        return prefixes.contains(candidate.substring(0, prefixLength)) && suffixes.contains(candidate.substring(prefixLength));
    }

    private ConcurrentSkipListSet<Concatenation6String> getInputWords(Object... args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Expected to get the words list as the argument");
        } else {
            if (args[0] instanceof ConcurrentSkipListSet) {
                return (ConcurrentSkipListSet<Concatenation6String>) args[0];
            } else {
                throw new IllegalArgumentException("Expected to get the words list as the argument");
            }
        }
    }
}
