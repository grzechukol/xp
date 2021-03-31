import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class BitCalculatorSpecification {

    private final BitCalculator calculator = new BitCalculator();

    @ParameterizedTest
    @ValueSource(strings = {
            "0",
            "100;5;255",
            "3 7  50   245",
            "  100;200 220      150;255",
            "$aa;$9f;$ff  $df  $a1",
            "200; $ab   $fe  155; $aa"
    })
    public void ShouldSuccessfullyCountNumberOf1Bits(String stringNumber) {
        // GIVEN
        var numberOf1Bits = getNumberOfBits1(stringNumber);

        // WHEN
        var noOfBits1 = calculator.noOfBits1(stringNumber);

        // THEN
        assertEquals(noOfBits1, numberOf1Bits);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "-1",
            "256",
            "15;255;290",
            "  100  300  400",
            "59;100   233  400",
            "100;200;$abc"
    })
    public void ShouldThrowIllegalArgumentExceptionWhenNumberOutOfRange(String wrongStringNumber) {
        // GIVEN && WHEN && THEN
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.noOfBits1(wrongStringNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "One",
            "a234",
            "100  abc ",
            "100;200;a5b",
            "$aa;$ff;$wxt"
    })
    public void ShouldThrowNumberFormatExceptionWhenCouldNotParseStringNumber(String wrongStringNumber) {
        // GIVEN && WHEN && THEN
        assertThrows(NumberFormatException.class, () -> {
            calculator.noOfBits1(wrongStringNumber);
        });
    }

    private int getNumberOfBits1(String stringNumbers) {
        return Arrays.stream(stringNumbers.trim().split(";|\\s"))
                .filter(str -> !str.equals(""))
                .map(this::parseInt)
                .map(Integer::bitCount)
                .reduce(0, Integer::sum);
    }

    private int parseInt(String s) {
        return s.startsWith("$") ? Integer.parseInt(s.substring(1), 16) : Integer.parseInt(s);
    }
}
