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
public class CalculatorTests {

 // 1
 public static Stream<Arguments> getParams_addsUpToTwoNumber_whenStringIsValid(){
  return Stream.of(Arguments.of("", 0), Arguments.of("1", 1), Arguments.of("3,2", 5));
 }

 @ParameterizedTest
 @MethodSource("getParams_addsUpToTwoNumber_whenStringIsValid")
 public void add_addsUpToTwoNumber_whenStringIsValid(String calculation, int expected) throws Exception {
  // Arrange
  var systemUnderTest = new Calculator();

  // Act
  var result = systemUnderTest.add(calculation);

  // Assert
  Assertions.assertEquals(expected, result);
 }


 // 2
 public static Stream<Arguments> getParams_addsUpToAnyNumber_whenStringIsValid(){
  return Stream.of(Arguments.of("2,3,4", 9), Arguments.of("20,10,90,20", 140));
 }

 @ParameterizedTest
 @MethodSource("getParams_addsUpToAnyNumber_whenStringIsValid")
 public void add_addsUpToAnyNumber_whenStringIsValid(String calculation, int expected) throws Exception {
  // Arrange
  var systemUnderTest = new Calculator();

  // Act
  var result = systemUnderTest.add(calculation);

  // Assert
  Assertions.assertEquals(expected, result);
 }


 // 3
 public static Stream<Arguments> getParams_addsNumbersUsingNewLineDelimiter_whenStringIsValid(){
  return Stream.of(Arguments.of("2\n3\n4", 9), Arguments.of("20\n10,90\n20", 140));
 }

 @ParameterizedTest
 @MethodSource("getParams_addsNumbersUsingNewLineDelimiter_whenStringIsValid")
 public void add_addsNumbersUsingNewLineDelimiter_whenStringIsValid(String calculation, int expected) throws Exception {
  // Arrange
  var systemUnderTest = new Calculator();

  // Act
  var result = systemUnderTest.add(calculation);

  // Assert
  Assertions.assertEquals(expected, result);
 }


 // 4
 public static Stream<Arguments> getParams_addsNumbersUsingCustomDelimiter_whenStringIsValid(){
  return Stream.of(Arguments.of("//;\n2;3;4", 9), Arguments.of("//;\n20\n10,90\n20", 140));
 }

 @ParameterizedTest
 @MethodSource("getParams_addsNumbersUsingCustomDelimiter_whenStringIsValid")
 public void add_addsNumbersUsingCustomDelimiter_whenStringIsValid(String calculation, int expected) throws Exception {
  // Arrange
  var systemUnderTest = new Calculator();

  // Act
  var result = systemUnderTest.add(calculation);

  // Assert
  Assertions.assertEquals(expected, result);
 }


 // 5
 public static Stream<Arguments> getParams_shouldThrowException_whenNegativeNumber(){
  return Stream.of(Arguments.of("//;\n-2;3;4", "-2"));
 }

 @ParameterizedTest
 @MethodSource("getParams_shouldThrowException_whenNegativeNumber")
 public void add_shouldThrowException_whenNegativeNumber(String calculation, String negativeNumbers)
 {
  // Arrange
  var systemUnderTest = new Calculator();

  // Act
  Exception exception = assertThrows(Exception.class, () -> systemUnderTest.add(calculation));
  String expectedMessage = "Negatives not allowed: " + negativeNumbers;
  String actualMessage = exception.getMessage();

  // Assert
  assertTrue(actualMessage.contains(expectedMessage));
 }
}
