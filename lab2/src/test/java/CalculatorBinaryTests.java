import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Parameterized.class)
public class CalculatorBinaryTests {
    // a
    public static Stream<Arguments> getParams_noOfBits1_returnsNumberOfBits1_whenStringIsValid(){
        return Stream.of(Arguments.of("12", 2), Arguments.of("40", 2));
    }

    @ParameterizedTest
    @MethodSource("getParams_noOfBits1_returnsNumberOfBits1_whenStringIsValid")
    public void noOfBits1_returnsNumberOfBits1_whenStringIsValid(String number, int expected) throws Exception {
        // Arrange
        var systemUnderTest = new CalculatorBinary();

        // Act
        var result = systemUnderTest.noOfBits1(number);

        // Assert
        Assertions.assertEquals(expected, result);
    }


    // b
    public static Stream<Arguments> getParams_noOfBits1_throwsException_ifNumberOutOfRange(){
        return Stream.of(Arguments.of("-2", 2), Arguments.of("-40", 2));
    }

    @ParameterizedTest
    @MethodSource("getParams_noOfBits1_throwsException_ifNumberOutOfRange")
    public void noOfBits1_throwsException_ifNumberOutOfRange(String number, int expected) throws Exception {
        // Arrange
        var systemUnderTest = new CalculatorBinary();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            systemUnderTest.noOfBits1(number);
        });
        String expectedMessage = "Number out of range!";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // c, d, e
    public static Stream<Arguments> getParams_noOfBits1_returnsNoOfBits1_whenNumbersSplitWithSemicolonOrAnyNumberOfWhiteSpaces(){
        return Stream.of(Arguments.of("10 3", 4), Arguments.of("10;3", 4), Arguments.of("10   3", 4), Arguments.of("10\n3", 4));
    }

    @ParameterizedTest
    @MethodSource("getParams_noOfBits1_returnsNoOfBits1_whenNumbersSplitWithSemicolonOrAnyNumberOfWhiteSpaces")
    public void noOfBits1_returnsNoOfBits1_whenNumbersSplitWithSemicolonOrAnyNumberOfWhiteSpaces(String numbers, int expected) throws Exception {
        // Arrange
        var systemUnderTest = new CalculatorBinary();

        // Act
        var result = systemUnderTest.noOfBits1(numbers);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    // f
    public static Stream<Arguments> getParams_noOfBits1_throwsException_ifWrongDelimiter(){
        return Stream.of(Arguments.of("2m4", 2), Arguments.of("4,7,8", 2));
    }

    @ParameterizedTest
    @MethodSource("getParams_noOfBits1_throwsException_ifWrongDelimiter")
    public void noOfBits1_throwsException_ifWrongDelimiter(String number, int expected) throws Exception {
        // Arrange
        var systemUnderTest = new CalculatorBinary();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            systemUnderTest.noOfBits1(number);
        });
        String expectedMessage = "Invalid number passed";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
