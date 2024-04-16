package com.sasimykyta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sasimykyta.model.EvenOddNumberFilter;
import org.junit.jupiter.api.Test;

public class EvenOddNumberFilterTest {
    @Test
    public void testFilterEvenCount() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Arrays.asList(1, 2, 3, 4));
        assertArrayEquals(new Integer[]{2, 4}, results.toArray());
    }

    @Test
    public void testFilterOddCount() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Arrays.asList(1, 2, 3, 4, 5));
        assertArrayEquals(new Integer[]{1, 3, 5}, results.toArray());
    }

    @Test
    public void testEmptyList() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Collections.emptyList());
        assertTrue(results.isEmpty(), "Filter should return an empty list when input is empty");
    }

    @Test
    public void testAllEvenNumbers() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Arrays.asList(2, 4, 6, 8));
        assertArrayEquals(new Integer[]{2, 4, 6, 8}, results.toArray(), "All even numbers should be returned for even-sized list");
    }

    @Test
    public void testAllOddNumbers() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Arrays.asList(1, 3, 5, 7));
        assertTrue(results.isEmpty(), "No numbers should be returned for even-sized list with all odd numbers");
    }

    @Test
    public void testNegativeNumbers() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Arrays.asList(-1, -2, -3, -4));
        assertArrayEquals(new Integer[]{-2, -4}, results.toArray(), "Negative even numbers should be returned for even-sized list");
    }

    @Test
    public void testMixedNumbers() {
        EvenOddNumberFilter filter = new EvenOddNumberFilter();
        List<Integer> results = filter.filterNumbers(Arrays.asList(-1, 0, 1, 2));
        assertArrayEquals(new Integer[]{0, 2}, results.toArray(), "Zero and positive even number should be returned for even-sized list");
    }
}
