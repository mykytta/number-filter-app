package com.sasimykyta.controller;

import com.sasimykyta.exception.ConfigurationException;
import com.sasimykyta.exception.DataReadException;
import com.sasimykyta.exception.DataWriteException;
import com.sasimykyta.model.EvenOddNumberFilter;
import com.sasimykyta.view.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DataProcessor {
    private static final Logger logger = Logger.getLogger(DataProcessor.class.getName());

    private DataReader reader;
    private DataWriter writer;
    private final EvenOddNumberFilter filter;

    public DataProcessor() {
        this.filter = new EvenOddNumberFilter();
    }

    public void start(String[] args) {
        try {
            if (args == null || args.length < 1) {
                throw new ConfigurationException("Missing input parameter.");
            }
            configure(args);
            List<Integer> numbers = reader.read();
            if (numbers == null || numbers.isEmpty()) {
                throw new DataReadException("No data read from the input source", new IllegalArgumentException());
            }
            List<Integer> filteredNumbers = filter.filterNumbers(numbers);
            if (filteredNumbers.isEmpty()) {
                logger.info("No numbers matched the filter criteria.");
            }
            writer.write(filteredNumbers);
        } catch (ConfigurationException | DataReadException | DataWriteException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            System.err.println(e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred: ", e);
            System.err.println("An unexpected error occurred.");
        }
    }

    private void configure(String[] args) {
        if (args[0].matches("\\d+")) {
            this.reader = new ConsoleDataReader(Integer.parseInt(args[0]));
            this.writer = new ConsoleDataWriter();
        } else {
            this.reader = new FileDataReader(args[0]);
            this.writer = (args.length > 1) ? new FileDataWriter(args[1]) : new ConsoleDataWriter();
        }
    }
}
