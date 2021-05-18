package com.transactions;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TransactionReaderTests {

    @Test
    public void Constructor_ShouldThrowError_WhenNoFileFound() {
        // Arrange
        var sut = new TransactionSaver();

        // Act
        IOException exception = assertThrows(IOException.class, () -> {
            new TransactionReader("./test/noSuchFile");
        });

        String expectedMessage = "";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void Constructor_ShouldOpenDefaultFile_WhenNoFileProvided() throws Exception {
        var sut = new TransactionReader();
        assertEquals(TransactionSaver.DEFAULT_FILE, sut.getFileName());
    }


    @Test
    public void ReadData_ShouldReadCsvFileWithoutColumnLine() throws Exception {
        // Arrange
        var sut = new TransactionReader();

        // Act
        var result = sut.readFile();
        var expected = new ArrayList<String[]>();
        expected.add(new String[]{ "1","Transaction1","Transaction1 Description","10-05-2021","EUR" });
        expected.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD" });

        // Assert
        assertArrayEquals(expected.toArray(), result.toArray());
    }


    @Test
    public void ReadData_ShouldReadCsvColumns() throws Exception {
        // Arrange
        var sut = new TransactionReader();

        // Act
        var result = sut.getColumnNames();
        var expected = new String[] {"ID","Name","Description","Date","Currency"};

        // Assert
        assertArrayEquals(expected, result);
    }
}
