package com.transactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class TransactionReaderTests {

    @BeforeEach
    public void Setup() {
        File testDir = new File("./test");
        testDir.mkdir();

        var fileName = "./test/file";
        var expected = new ArrayList<String[]>();
        expected.add(new String[]{ "1","Transaction1","Transaction1 Description","10-05-2021","EUR" });
        expected.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD" });

        try {
            File file = new File(fileName + ".csv");
            file.createNewFile();
            Writer fileWriter = new FileWriter(fileName + ".csv", false);
            var columns = Arrays.asList(
                    "ID", "Name", "Description", "Date", "Currency"
            );
            fileWriter.append(String.join(";", columns));
            fileWriter.append("\n");
            fileWriter.append(String.join(";", expected.get(0)));
            fileWriter.append("\n");
            fileWriter.append(String.join(";", expected.get(1)));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void CleanUp() {
        try {
            var dir = new File("./test");
            for (File file : dir.listFiles())
                file.delete();
            dir.delete();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

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
        var fileName = "./test/file";
        var sut = new TransactionReader(fileName);
        var expected = new ArrayList<String[]>();
        expected.add(new String[]{ "1","Transaction1","Transaction1 Description","10-05-2021","EUR" });
        expected.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD" });

        // Act
        var result = sut.readFile();

        // Assert
        assertArrayEquals(expected.toArray(), result.toArray());
    }


    @Test
    public void ReadData_ShouldReadCsvColumns() throws Exception {
        // Arrange
        var fileName = "./test/file";
        var sut = new TransactionReader(fileName);
        // Act
        var result = sut.getColumnNames();
        var expected = new String[] {"ID","Name","Description","Date","Currency"};

        // Assert
        assertArrayEquals(expected, result);
    }
}
