package com.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BinaryCalculatorTests {

    private static Stream<Arguments> stringToValue() {
        return Stream.of(
                arguments("", 0),
                arguments("0", 0),
                arguments("1", 1),
                arguments("2", 1),
                arguments("5", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValue")
    public void Add_AddsUpToTwoNumbers_WhenStringIsValid(String number, int expected) throws Exception {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        var result = sut.noOfBits1(number);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-21", "256"})
    public void Add_ShouldThrowException_WhenNegativeNumber(String calculation) {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            sut.noOfBits1(calculation);
        });
        String expectedMessage = "Number is out of bounds";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private static Stream<Arguments> stringToValueMore() {
        return Stream.of(
                arguments("1;3", 3),
                arguments("2;5", 3),
                arguments("1;2;5;8", 5)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValueMore")
    public void Add_AddsUpToAnyNumbers_WhenStringIsValid(String number, int expected) throws Exception {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        var result = sut.noOfBits1(number);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> stringToValueMoreAndSpaces() {
        return Stream.of(
                arguments("1;3", 3),
                arguments("2 5", 3),
                arguments("1;2 5;8", 5)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValueMoreAndSpaces")
    public void Add_AddsUpToAnyNumbersWithSpaces_WhenStringIsValid(String number, int expected) throws Exception {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        var result = sut.noOfBits1(number);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> stringToValueMoreAndAnyWhiteChars() {
        return Stream.of(
                arguments("1;3", 3),
                arguments("2 5", 3),
                arguments("1    2 5\n8;1", 6)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValueMoreAndAnyWhiteChars")
    public void Add_AddsUpToAnyNumbersWithAnyWhiteChars_WhenStringIsValid(String number, int expected) throws Exception {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        var result = sut.noOfBits1(number);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"23,3", "123;21|1"})
    public void Add_ShouldThrowException_WhenDifferentSeparator(String calculation) {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            sut.noOfBits1(calculation);
        });
        String expectedMessage = "Cannot parse numbers";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private static Stream<Arguments> stringToHexaValue() {
        return Stream.of(
                arguments("1;$ff", 9),
                arguments("2 $5", 3),
                arguments("1    $c;1", 4)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToHexaValue")
    public void Add_AddsUpToAnyNumbersWithAnyHexa_WhenStringIsValid(String number, int expected) throws Exception {
        // Arrange
        var sut = new BinaryCalculator();

        // Act
        var result = sut.noOfBits1(number);

        // Assert
        assertEquals(expected, result);
    }
}
