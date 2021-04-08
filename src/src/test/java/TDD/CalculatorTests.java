package TDD;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    private static Calculator sut;

    @BeforeAll
    public static void setup() {
        sut = new Calculator();
    }

    @Test
    @DisplayName("Adds up to two numbers")
    void addsTwoNumbers () throws Exception {
        Assertions.assertEquals(0, sut.add(""));
        Assertions.assertEquals(1, sut.add("1"));
        Assertions.assertEquals(3, sut.add("1,2"));
    }

    @Test
    @DisplayName("Adds up to any numbers")
    void addsAnyNumbers () throws Exception  {
        Assertions.assertEquals(6, sut.add("1,2,3"));
        Assertions.assertEquals(130, sut.add("10,90,10,20"));
    }

    @Test
    @DisplayName("Adds numbers using new line delimiters")
    void addsNumbersWithNewLineDelimiters () throws Exception  {
        Assertions.assertEquals(6, sut.add("1\n2,3"));
        Assertions.assertEquals(130, sut.add("10\n90,10\n20"));
    }

    @Test
    @DisplayName("Adds numbers using custom delimiters ")
    void addsNumbersWithCustomDelimiters () throws Exception  {
        Assertions.assertEquals(3, sut.add("//;\n1;2"));
        Assertions.assertEquals(7, sut.add("//;\n1;2;4"));
    }

    @Test
    @DisplayName("Negative numbers should throw exception")
    void negativeNumbersThrowException () {
        Assertions.assertThrows(Calculator.NegativeNumbersException.class, ()->sut.add("1,2,-1"));
        Assertions.assertThrows(Calculator.NegativeNumbersException.class, ()->sut.add("//;\n1;-2;-4"));
    }

}
