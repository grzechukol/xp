import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class CalculatorTests {

 public static Stream<Arguments> provideParameters(){
  return Stream.of(Arguments.of("", 0), Arguments.of("1", 1), Arguments.of("1,2", 3));
 }

 @ParameterizedTest
 @MethodSource("provideParameters")
 public void add_addsUpToTwoNumber_whenStringIsValid(String calculation, int expected){
  // Arrange
  var systemUnderTest = new Calculator();

  // Act
  var result = systemUnderTest.Add(calculation);

  // Assert
  Assertions.assertEquals(expected, result);
 }
}
