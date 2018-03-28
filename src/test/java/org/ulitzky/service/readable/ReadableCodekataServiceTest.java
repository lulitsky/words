package org.ulitzky.service.readable;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lulitzky on 28.03.18.
 */
public class ReadableCodekataServiceTest {

    ReadableCodekataService  service = new ReadableCodekataService();

    
    @Test
    public void testProcessInputWordsEmptyInput() {
        assertTrue(service.processInputWords(Collections.<String>emptyList()).isEmpty());
    }

    @Test
    public void testProcessInputWordsNoResults() {
        List<String> input = Arrays.asList("123456", "1", "12", "123", "1234", "12345", "1234567");
        assertTrue(service.processInputWords(input).isEmpty());

    }


    @Test
    public void testProcessInputWordsasResults() {
        List<String> input = Arrays.asList("123456", "654321", "1", "12", "123", "1234", "12345", "1234567", "56");
        List<String> result = service.processInputWords(input);
        assertEquals(1, result.size());
        String resultLine = result.get(0);
        assertTrue(resultLine.contains("123456"));
    }

}
