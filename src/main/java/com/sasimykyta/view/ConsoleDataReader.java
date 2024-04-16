package com.sasimykyta.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleDataReader implements DataReader {
    private final int numberOfInputs;
    private final Scanner scanner;
    public ConsoleDataReader(int numberOfInputs){
        this.numberOfInputs = numberOfInputs;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<Integer> read() {
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Please enter " + numberOfInputs + " integer numbers:");
        while (numbers.size() < numberOfInputs) {
            if (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return numbers;
    }
}
