package com.sasimykyta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import com.sasimykyta.view.ConsoleDataWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsoleDataWriterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testWrite() {
        ConsoleDataWriter writer = new ConsoleDataWriter();
        writer.write(Arrays.asList(1, 2, 3));
        assertEquals("1\n2\n3\n", outContent.toString());
    }

    @Test
    public void testWriteEmptyList() {
        ConsoleDataWriter writer = new ConsoleDataWriter();
        writer.write(Collections.emptyList());
        assertEquals("", outContent.toString(), "Output should be empty for an empty input list.");
    }

    @Test
    public void testWriteNegativeNumbers() {
        ConsoleDataWriter writer = new ConsoleDataWriter();
        writer.write(Arrays.asList(-1, -2, -3));
        assertEquals("-1\n-2\n-3\n", outContent.toString(), "Output should correctly display negative numbers.");
    }

    @Test
    public void testWriteLargeNumbers() {
        ConsoleDataWriter writer = new ConsoleDataWriter();
        writer.write(Arrays.asList(999999999, 1234567890, 2147483647));
        assertEquals("999999999\n1234567890\n2147483647\n", outContent.toString(), "Output should correctly display large numbers.");
    }

    @Test
    public void testWriteMixedTypes() {
        ConsoleDataWriter writer = new ConsoleDataWriter();
        writer.write(Arrays.asList(0, -10, 100));
        assertEquals("0\n-10\n100\n", outContent.toString(), "Output should correctly handle a mix of zero, negative, and positive numbers.");
    }
}
