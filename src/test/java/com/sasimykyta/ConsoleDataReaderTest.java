package com.sasimykyta;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.sasimykyta.view.ConsoleDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsoleDataReaderTest {
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void restoreStreams() {
        System.setIn(originalIn);
    }

    @Test
    public void testReadValidInput() {
        String data = "10 20 30\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ConsoleDataReader reader = new ConsoleDataReader(3);
        List<Integer> results = reader.read();
        assertArrayEquals(new Integer[] {10, 20, 30}, results.toArray());
    }

    @Test
    public void testReadInvalidInput() {
        String data = "10 two 30\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ConsoleDataReader reader = new ConsoleDataReader(3);
        assertThrows(RuntimeException.class, reader::read);
    }

    @Test
    public void testReadNegativeAndZeroInput() {
        String data = "-10 0 20\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ConsoleDataReader reader = new ConsoleDataReader(3);
        List<Integer> results = reader.read();
        assertArrayEquals(new Integer[] {-10, 0, 20}, results.toArray());
    }

    @Test
    public void testReadLongNumber() {
        String data = "9999999999 12345678901234567890 20\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ConsoleDataReader reader = new ConsoleDataReader(3);
        assertThrows(RuntimeException.class, reader::read);
    }

    @Test
    public void testReadStringInsteadOfInteger() {
        String data = "hello world 123\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ConsoleDataReader reader = new ConsoleDataReader(3);
        assertThrows(RuntimeException.class, reader::read);
    }
}

