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
        Assertions.assertEquals(sut.noOfBits1("255"), 8);
        Assertions.assertEquals(sut.noOfBits1("1"), 1);
        Assertions.assertEquals(sut.noOfBits1("112"), 3);
    }

    @Test
    @DisplayName("Throws exception for numbers outside range 0-255")
    void throwsForNumbersOutsideRange () throws Exception {
        Assertions.assertThrows(BitCounter.OutOfBoundsException.class, ()->sut.noOfBits1("256"));
        Assertions.assertThrows(BitCounter.OutOfBoundsException.class, ()->sut.noOfBits1("-3"));
    }

    @Test
    @DisplayName("Counts ones for multiple number split with semicolon")
    void worksForMultipleNumbers () throws Exception {
        Assertions.assertEquals(sut.noOfBits1("255;1;112"), 12);
        Assertions.assertEquals(sut.noOfBits1("1;112"), 4);
    }

    @Test
    @DisplayName("Counts ones for multiple number split with whitespace characters")
    void worksForMultipleNumbersWhitespace () throws Exception {
        Assertions.assertEquals(sut.noOfBits1("255 1    112"), 12);
        Assertions.assertEquals(sut.noOfBits1("1\n112"), 4);
    }

    @Test
    @DisplayName("Throws exception for numbers outside range 0-255")
    void throwsForWrongFormat () throws Exception {
        Assertions.assertThrows(BitCounter.WrongFormatException.class, ()->sut.noOfBits1("1 something_weird 23"));
    }

    @Test
    @DisplayName("Counts ones for hex numbers")
    void worksForHexNumbers () throws Exception {
        Assertions.assertEquals(sut.noOfBits1("255;$1"), 12);
        Assertions.assertEquals(sut.noOfBits1("1;$70"), 4);
    }

}
