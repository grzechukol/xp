package pl.edu.agh.xp.lab2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BitCalculatorTests {

	private static BitCalculator sut;

	@BeforeAll
	public static void setup() {
		sut = new BitCalculator();
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
		Assertions.assertThrows(BitCalculator.NumberOutOfBoundsException.class, ()->sut.noOfBits1("256"));
		Assertions.assertThrows(BitCalculator.NumberOutOfBoundsException.class, ()->sut.noOfBits1("-3"));
	}

	@Test
	@DisplayName("Counts ones for multiple number split with semicolon")
	void worksForMultipleNumbers () throws Exception {
		Assertions.assertEquals(2, sut.noOfBits1("1;32"));
		Assertions.assertThrows(BitCalculator.NumberOutOfBoundsException.class, ()->sut.noOfBits1("1;33;22"));
	}
//
	@Test
	@DisplayName("Counts ones for multiple number split with whitespace characters")
	void worksForMultipleNumbersWhitespace () throws Exception {
		Assertions.assertEquals(3, sut.noOfBits1(" 1    12"));
		Assertions.assertEquals(3, sut.noOfBits1("1\n12"));
	}

	@Test
	@DisplayName("Throws exception for badly formatted input")
	void throwsForWrongFormat () {
		Assertions.assertThrows(BitCalculator.NumbersSeparatorException.class, ()->sut.noOfBits1("1aaaa23"));
	}

	@Test
	@DisplayName("Counts ones for hex numbers")
	void worksForHexNumbers () throws Exception {
		Assertions.assertEquals(1, sut.noOfBits1("$1"));
		Assertions.assertEquals(4, sut.noOfBits1("1;$70"));
	}
}
