package org.ulitzky.service.extendable;

import org.junit.Test;
import org.ulitzky.service.effective.EffectiveCodekataService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lulitzky on 28.03.18.
 */
public class ExtendableCodekataServiceTest {
    ExtendableCodekataService service = new ExtendableCodekataService();



    @Test
    public void testProcessInputWordsEmptyInput() {
        ConcurrentSkipListSet<IProcessable> input = new         ConcurrentSkipListSet<IProcessable>();

        assertTrue(service.processInput(input).isEmpty());
    }


    @Test
    public void testProcessInputWordsNoResults() {
        ConcurrentSkipListSet<IProcessable> input = new         ConcurrentSkipListSet<IProcessable>();
        input.add(new Concatenation6String("1"));
        input.add(new Concatenation6String("12"));
        input.add(new Concatenation6String("123"));
        input.add(new Concatenation6String("1234"));
        input.add(new Concatenation6String("12345"));
        input.add(new Concatenation6String("123456"));
        input.add(new Concatenation6String("1234567"));

        assertTrue(service.processInput(input).isEmpty());
    }



    @Test
    public void testProcessInputWordsHasResults() {
        ConcurrentSkipListSet<IProcessable> input = new         ConcurrentSkipListSet<IProcessable>();
        input.add(new Concatenation6String("1"));
        input.add(new Concatenation6String("12"));
        input.add(new Concatenation6String("123"));
        input.add(new Concatenation6String("1234"));
        input.add(new Concatenation6String("3456"));
        input.add(new Concatenation6String("12345"));
        input.add(new Concatenation6String("123456"));
        input.add(new Concatenation6String("654321"));
        input.add(new Concatenation6String("1234567"));

        List<String> result = service.processInput(input);
        assertEquals(1, result.size());
        String resultLine = result.iterator().next();
        assertTrue(resultLine.contains("123456"));    }



}
