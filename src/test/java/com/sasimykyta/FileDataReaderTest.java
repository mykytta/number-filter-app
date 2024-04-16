package com.sasimykyta;

import static org.junit.jupiter.api.Assertions.*;

import com.sasimykyta.view.FileDataReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class FileDataReaderTest {
    private Path tempFile;
    private final Logger logger = Logger.getLogger(FileDataReader.class.getName());

    @BeforeEach
    public void setUp() throws Exception {
        tempFile = Files.createTempFile("test", ".txt");
    }

    @AfterEach
    public void tearDown() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    private void writeToTempFile(String data) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
            writer.write(data);
        }
    }

    @Test
    public void testReadValidNumbers() throws Exception {
        String data = "1 2 3 4 5";
        writeToTempFile(data);

        FileDataReader reader = new FileDataReader(tempFile.toString());
        List<Integer> results = reader.read();
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, results.toArray());
    }

    @Test
    public void testHandleInvalidInput() throws Exception {
        String data = "1 2 three 4 5";
        writeToTempFile(data);

        FileDataReader reader = new FileDataReader(tempFile.toString());
        List<Integer> results = reader.read();
        assertEquals(4, results.size());
        assertTrue(results.containsAll(Arrays.asList(1, 2, 4, 5) ));
    }

    @Test
    public void testReadNegativeAndZero() throws Exception {
        String data = "-1 0 2 -3 4";
        writeToTempFile(data);

        FileDataReader reader = new FileDataReader(tempFile.toString());
        List<Integer> results = reader.read();
        assertArrayEquals(new Integer[]{-1, 0, 2, -3, 4}, results.toArray());
    }

    @Test
    public void testReadLargeNumbers() throws Exception {
        String data = "9999999999 12345678901234567890 2147483647";
        writeToTempFile(data);

        FileDataReader reader = new FileDataReader(tempFile.toString());
        List<Integer> results = reader.read();
        assertEquals(1, results.size());
        assertTrue(results.contains(2147483647));
    }


    @Test
    public void testReadFileWithOnlyNonNumericStrings() throws Exception {
        String data = "hello world test";
        writeToTempFile(data);

        FileDataReader reader = new FileDataReader(tempFile.toString());
        List<Integer> results = reader.read();
        assertTrue(results.isEmpty());
    }
}
