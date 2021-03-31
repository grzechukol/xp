public class BitCalculator {
    private static final int UPPER_BOUND = 255;
    private static final int LOWER_BOUND = 0;
    private static final int HEXADECIMAL = 16;
    private static final String HEXADECIMAL_PREFIX = "$";

    public int noOfBits1(String stringNumbers) {
        var totalNoOfBits1 = 0;
        var splittedStringNumbers = stringNumbers.trim().split(";|\\s");
        for (var stringNumber : splittedStringNumbers) {
            if (!stringNumber.equals("")) {
                var number = stringNumber.startsWith(HEXADECIMAL_PREFIX)
                        ? Integer.parseInt(stringNumber.substring(1), HEXADECIMAL)
                        : Integer.parseInt(stringNumber);
                if (LOWER_BOUND > number || number > UPPER_BOUND) {
                    throw new IllegalArgumentException();
                }
                totalNoOfBits1 += Integer.bitCount(number);
            }
        }
        return totalNoOfBits1;
    }
}
