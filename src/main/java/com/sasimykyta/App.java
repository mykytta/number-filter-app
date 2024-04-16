package com.sasimykyta;

import com.sasimykyta.controller.DataProcessor;

public class App {
    public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.start(args);
    }
}
