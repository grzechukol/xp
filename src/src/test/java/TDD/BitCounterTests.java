package TDD;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BitCounterTests {

    private static BitCounter sut;

    @BeforeAll
    public static void setup() {
        sut = new BitCounter();
    }

    @Test
    @DisplayName("Counts ones in the decimal number")
    void worksForDecimal () throws Exception {
        Assertions.assertEquals(8, sut.noOfBits1("255"));
        Assertions.assertEquals(1, sut.noOfBits1("1"));
        Assertions.assertEquals(3, sut.noOfBits1("112"));
    }

    @Test
    @DisplayName("Throws exception for numbers outside range 0-255")
    void throwsForNumbersOutsideRange () {
        Assertions.assertThrows(BitCounter.NumberOutOfBoundsException.class, ()->sut.noOfBits1("256"));
        Assertions.assertThrows(BitCounter.NumberOutOfBoundsException.class, ()->sut.noOfBits1("-3"));
    }

    @Test
    @DisplayName("Counts ones for multiple number split with semicolon")
    void worksForMultipleNumbers () throws Exception {
        Assertions.assertEquals(12, sut.noOfBits1("255;1;112"));
        Assertions.assertEquals(4, sut.noOfBits1("1;112"));
    }

    @Test
    @DisplayName("Counts ones for multiple number split with whitespace characters")
    void worksForMultipleNumbersWhitespace () throws Exception {
        Assertions.assertEquals(12, sut.noOfBits1("255 1    112"));
        Assertions.assertEquals(4, sut.noOfBits1("1\n112"));
    }

    @Test
    @DisplayName("Throws exception for badly formatted input")
    void throwsForWrongFormat () {
        Assertions.assertThrows(IllegalArgumentException.class, ()->sut.noOfBits1("1 something_weird 23"));
    }

    @Test
    @DisplayName("Counts ones for hex numbers")
    void worksForHexNumbers () throws Exception {
        Assertions.assertEquals(9, sut.noOfBits1("255;$1"));
        Assertions.assertEquals(4, sut.noOfBits1("1;$70"));
    }

}
