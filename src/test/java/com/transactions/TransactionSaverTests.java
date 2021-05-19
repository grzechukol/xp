package com.transactions;

import com.calculator.Calculator;
import com.calculator.NegativeNumberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TransactionSaverTests {

    @BeforeEach
    public void Setup() {
        File testDir = new File("./test");
        testDir.mkdir();

        var fileName = "./test/file";

        try {
            File file = new File(fileName + ".csv");
            file.createNewFile();
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

//    @Test
//    public void Constructor_ShouldCreateDefaultColumnNames_WhenNoArgsProvided() throws Exception {
//        // Arrange
//        var expectedColumnNames = Arrays.asList(
//                "ID", "Name", "Description", "Date", "Currency"
//        );
//
//        // Act
//        var sut = new TransactionSaver();
//
//        // Assert
//        assertEquals(sut.getFileName(), "./src/main/resources/transactions.csv");
//        assertEquals(sut.getColumnNames(), expectedColumnNames);
//    }

    private static Stream<Arguments> filesNamesAndColumnsToTest() {
        return Stream.of(
                arguments("file", Arrays.asList("")),
                arguments("file", Arrays.asList("a")),
                arguments("file", Arrays.asList("a","b","c"))
        );
    }

    @ParameterizedTest
    @MethodSource("filesNamesAndColumnsToTest")
    public void Constructor_ShouldCreateCorrectColumnNames_WhenArgsProvided(String fileName, List<String> columns) throws Exception {
        // Arrange
        // Act
        var sut = new TransactionSaver("./test/" + fileName, columns);

        // Assert
        assertEquals(sut.getFileName(), "./test/" + fileName + ".csv");
        assertEquals(sut.getColumnNames(), columns);
    }

    private static Stream<Arguments> columnsToTest() {
        return Stream.of(
                arguments(Arrays.asList("")),
                arguments(Arrays.asList("a")),
                arguments(Arrays.asList("a","b","c"))
        );
    }

    @ParameterizedTest
    @MethodSource("columnsToTest")
    public void AppendDataToFile_ThrowsIncorrectDataException_WhenWrongNumberOfFieldsProvided(List<String> data) {
        // Arrange
        var sut = new TransactionSaver();

        // Act
        IncorrectDataException exception = assertThrows(IncorrectDataException.class, () -> {
            sut.appendDataToFile(data);
        });
        String expectedMessage = "Number of fields doesn't match number of labels";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @MethodSource("filesNamesAndColumnsToTest")
    public void AppendDataToFile_ThrowsIOException_WhenDirectoryWithSamePathExists(String fileName, List<String> columns) throws IOException {
        // Arrange
        var file = "./test/exists";
        Files.createDirectories(Paths.get(file + ".csv"));
        var sut = new TransactionSaver(file, columns);

        // Act
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            sut.appendDataToFile(columns);
        });
        String expectedMessage = file + ".csv (Is a directory)" ;
        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    private static Stream<Arguments> filesNamesAndFileContent() {
        return Stream.of(
                arguments("file", Arrays.asList(""), Arrays.asList(""), ""),
                arguments("file", Arrays.asList("a"), Arrays.asList("b"), "b"),
                arguments("file", Arrays.asList("d","e","f"), Arrays.asList("a","b","c"), "a;b;c")
        );
    }

    private static Stream<Arguments> filesNamesAndFileContentWithTwoLines() {
        return Stream.of(
                arguments("file", Arrays.asList(""), Arrays.asList(""), "\n\n"),
                arguments("newFile", Arrays.asList("a"), Arrays.asList("b"), "a\nb\n"),
                arguments("XYZ", Arrays.asList("d","e","f"), Arrays.asList("a","b","c"), "d;e;f\na;b;c\n")
        );
    }

    @ParameterizedTest
    @MethodSource("filesNamesAndFileContent")
    public void AppendDataToFile_ShouldCreateCorrectDataInFile_WhenCorrectDataProvided(String fileName, List<String> columns, List<String> data, String expectedContent) throws Exception {
        // Arrange
        var sut = new TransactionSaver("./test/" + fileName, columns);
        var actualContent = "";

        // Act
        sut.appendDataToFile(data);

        // Assert
        File file = new File("./test/" + fileName + ".csv");
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            actualContent += line;
        }
        myReader.close();
        assertEquals(expectedContent, actualContent);
    }

    @ParameterizedTest
    @MethodSource("filesNamesAndFileContentWithTwoLines")
    public void AppendDataToFile_ShouldCreateCorrectDataInFileWithHeader_WhenCorrectDataProvided(String fileName, List<String> columns, List<String> data, String expectedContent) throws Exception {
        // Arrange
        var sut = new TransactionSaver("./test/" + fileName, columns);
        var actualContent = "";

        // Act
        sut.appendDataToFile(columns);
        sut.appendDataToFile(data);

        // Assert
        File file = new File("./test/" + fileName + ".csv");
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            actualContent += line + "\n";
        }
        myReader.close();
        assertEquals(actualContent, expectedContent);
    }
}
