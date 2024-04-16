package com.sasimykyta.view;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FileDataReader implements DataReader {
    private static final Logger logger = Logger.getLogger(FileDataReader.class.getName());

    private final String filePath;
    public FileDataReader(String filePath){
        this.filePath = filePath;
    }
    @Override
    public List<Integer> read() {
        List<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    numbers.add(scanner.nextInt());
                } else {
                    String invalidInput = scanner.next();
                    logger.log(Level.SEVERE, "Skipping invalid input: " + invalidInput);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read file: " + filePath, e);
            throw new RuntimeException("Failed to read from file: " + filePath, e);
        }
        return numbers;
    }
}
