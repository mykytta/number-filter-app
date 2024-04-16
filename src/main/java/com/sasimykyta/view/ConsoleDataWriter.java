package com.sasimykyta.view;

import java.util.List;

public class ConsoleDataWriter implements DataWriter {
    @Override
    public void write(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }
}
