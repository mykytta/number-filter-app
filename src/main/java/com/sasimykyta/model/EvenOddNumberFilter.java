package com.sasimykyta.model;

import java.util.List;
import java.util.stream.Collectors;

public class EvenOddNumberFilter implements NumberFilter {
    @Override
    public List<Integer> filterNumbers(List<Integer> numbers) {
        boolean isEven = numbers.size() % 2 == 0;
        return numbers.stream()
                .filter(n -> isEven == (n % 2 == 0))
                .collect(Collectors.toList());
    }
}
