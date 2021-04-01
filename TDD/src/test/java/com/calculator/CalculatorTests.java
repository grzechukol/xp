package com.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CalculatorTests {

    private static Stream<Arguments> stringToValue() {
        return Stream.of(
                arguments("", 0),
                arguments("1", 1),
                arguments("1,2", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValue")
    public void Add_AddsUpToTwoNumbers_WhenStringIsValid(String calculation, int expected) throws Exception {
        // Arrange
        var sut = new Calculator();

        // Act
        var result = sut.add(calculation);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> stringToValueMore() {
        return Stream.of(
                arguments("1,2,3", 6),
                arguments("10,90,10,20", 130)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValueMore")
    public void Add_AddsUpToAnyNumber_WhenStringIsValid(String calculation, int expected) throws Exception {
        // Arrange
        var sut = new Calculator();

        // Act
        var result = sut.add(calculation);

        // Assert
        assertEquals(expected, result);
    }


    private static Stream<Arguments> stringToValueWithNewLine() throws Exception {
        return Stream.of(
                arguments("1\n2,3", 6),
                arguments("10\n90,10\n20", 130)
        );
    }
    @ParameterizedTest
    @MethodSource("stringToValueWithNewLine")
    public void Add_AddsUpToAnyNumberWithNewLine_WhenStringIsValid(String calculation, int expected) throws Exception {
        // Arrange
        var sut = new Calculator();

        // Act
        var result = sut.add(calculation);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> stringToValueWithCustom() {
        return Stream.of(
                arguments("//;\n1;2;3", 6),
                arguments("//;\n10;90;10;20", 130)
        );
    }

    @ParameterizedTest
    @MethodSource("stringToValueWithCustom")
    public void Add_AddsUpToAnyNumberWithCustomDelimiter_WhenStringIsValid(String calculation, int expected) throws Exception {
        // Arrange
        var sut = new Calculator();

        // Act
        var result = sut.add(calculation);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("stringToValueWithNegative")
    public void Add_ShouldThrowException_WhenNegativeNumber(String calculation, String negativeNumbers) {
        // Arrange
        var sut = new Calculator();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            sut.add(calculation);
        });
        String expectedMessage = "Negatives not allowed: " + negativeNumbers;
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
