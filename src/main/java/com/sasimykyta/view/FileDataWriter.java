package com.sasimykyta.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FileDataWriter implements DataWriter {
    private static final Logger logger = Logger.getLogger(FileDataWriter.class.getName());
    private final String filePath;

    public FileDataWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(List<Integer> numbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Integer number : numbers) {
                writer.write(number.toString());
                writer.newLine();
            }
            logger.info("Successfully wrote " + numbers.size() + " numbers to file: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write to file: " + filePath, e);
            throw new RuntimeException("Error writing to file: " + filePath, e);
        }
    }
}
