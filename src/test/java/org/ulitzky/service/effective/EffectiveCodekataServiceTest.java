package org.ulitzky.service.effective;

import org.junit.Test;
import org.ulitzky.service.readable.ReadableCodekataService;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lulitzky on 28.03.18.
 */
public class EffectiveCodekataServiceTest {

    EffectiveCodekataService  service = new EffectiveCodekataService();


    @Test
    public void testProcessInputWordsEmptyInput() {
        assertTrue(service.processInputWords(Collections.<Integer, ConcurrentSkipListSet<String>>emptyMap()).isEmpty());
    }

    @Test
    public void testProcessInputWordsNoResults() {
        Map<Integer, ConcurrentSkipListSet<String>> input = new TreeMap<>();
        input.put(1, new ConcurrentSkipListSet<String>(Arrays.asList("1", "2", "7")) );
        input.put(2, new ConcurrentSkipListSet<String>(Arrays.asList("12")) );
        input.put(3, new ConcurrentSkipListSet<String>(Arrays.asList("123")) );
        input.put(4, new ConcurrentSkipListSet<String>(Arrays.asList("1234")) );
        input.put(5, new ConcurrentSkipListSet<String>(Arrays.asList("12345")) );
        input.put(6, new ConcurrentSkipListSet<String>(Arrays.asList("123456")) );

        assertTrue(service.processInputWords(input).isEmpty());

    }


    @Test
    public void testProcessInputWordsInvalidInputNoResults() {
        Map<Integer, ConcurrentSkipListSet<String>> input = new TreeMap<>();
        input.put(1, new ConcurrentSkipListSet<String>(Arrays.asList("1", "2", "7")) );
        input.put(2, new ConcurrentSkipListSet<String>(Arrays.asList("12", "456")) );
        input.put(3, new ConcurrentSkipListSet<String>(Arrays.asList("123")) );
        input.put(4, new ConcurrentSkipListSet<String>(Arrays.asList("1234")) );
        input.put(5, new ConcurrentSkipListSet<String>(Arrays.asList("12345")) );
        input.put(6, new ConcurrentSkipListSet<String>(Arrays.asList("123456")) );

        assertTrue(service.processInputWords(input).isEmpty());

    }


    @Test
    public void testProcessInputWordsHasResults() {
        Map<Integer, ConcurrentSkipListSet<String>> input = new TreeMap<>();
        input.put(1, new ConcurrentSkipListSet<String>(Arrays.asList("1", "2", "7")) );
        input.put(2, new ConcurrentSkipListSet<String>(Arrays.asList("12")) );
        input.put(3, new ConcurrentSkipListSet<String>(Arrays.asList("123")) );
        input.put(4, new ConcurrentSkipListSet<String>(Arrays.asList("1234", "3456")) );
        input.put(5, new ConcurrentSkipListSet<String>(Arrays.asList("12345")) );
        input.put(6, new ConcurrentSkipListSet<String>(Arrays.asList("123456")) );

        Set<String> result = service.processInputWords(input);
        assertEquals(1, result.size());
        String resultLine = result.iterator().next();
        assertTrue(resultLine.contains("123456"));

    }


    @Test
    public void testProcessInputWordsInvalidInputHasResults() {
        Map<Integer, ConcurrentSkipListSet<String>> input = new TreeMap<>();
        input.put(1, new ConcurrentSkipListSet<String>(Arrays.asList("1", "2", "7")) );
        input.put(2, new ConcurrentSkipListSet<String>(Arrays.asList("12", "456")) );
        input.put(3, new ConcurrentSkipListSet<String>(Arrays.asList("123")) );
        input.put(4, new ConcurrentSkipListSet<String>(Arrays.asList("1234", "3456")) );
        input.put(5, new ConcurrentSkipListSet<String>(Arrays.asList("12345")) );
        input.put(6, new ConcurrentSkipListSet<String>(Arrays.asList("123456")) );

        Set<String> result = service.processInputWords(input);
        assertEquals(1, result.size());
        String resultLine = result.iterator().next();
        assertTrue(resultLine.contains("123456"));

    }
}
