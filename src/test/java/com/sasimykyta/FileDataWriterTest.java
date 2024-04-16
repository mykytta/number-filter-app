package com.sasimykyta;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sasimykyta.view.FileDataWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileDataWriterTest {
    private Path filePath;

    @BeforeEach
    public void setUp() throws IOException {
        filePath = Paths.get("test_output.txt");
        Files.deleteIfExists(filePath);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(filePath);
    }

    @Test
    public void testWriteToFile() throws Exception {
        FileDataWriter writer = new FileDataWriter(filePath.toString());
        writer.write(Arrays.asList(100, 200, 300));

        List<String> lines = Files.readAllLines(filePath);
        assertArrayEquals(new String[]{"100", "200", "300"}, lines.toArray(new String[0]));
    }

    @Test
    public void testWriteEmptyList() throws Exception {
        FileDataWriter writer = new FileDataWriter(filePath.toString());
        writer.write(Collections.emptyList());

        assertTrue(Files.exists(filePath), "File should exist even if it's empty.");
        assertTrue(Files.readAllLines(filePath).isEmpty(), "File should be empty when writing an empty list.");
    }

    @Test
    public void testWriteNegativeNumbers() throws Exception {
        FileDataWriter writer = new FileDataWriter(filePath.toString());
        writer.write(Arrays.asList(-1, -100, -1000));

        List<String> lines = Files.readAllLines(filePath);
        assertArrayEquals(new String[]{"-1", "-100", "-1000"}, lines.toArray(new String[0]));
    }

    @Test
    public void testWriteLargeNumbers() throws Exception {
        FileDataWriter writer = new FileDataWriter(filePath.toString());
        writer.write(Arrays.asList(Integer.MAX_VALUE, 999999999, 1234567890));

        List<String> lines = Files.readAllLines(filePath);
        assertArrayEquals(new String[]{String.valueOf(Integer.MAX_VALUE), "999999999", "1234567890"}, lines.toArray(new String[0]));
    }
}
