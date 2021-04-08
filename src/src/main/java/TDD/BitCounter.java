package TDD;

public class BitCounter {
    private static final int UPPER_BOUND = 255;
    private static final int LOWER_BOUND = 0;

    public static class NumberOutOfBoundsException extends Exception {
        public NumberOutOfBoundsException(String errorMessage) {
            super(errorMessage);
        }
    }

    public int noOfBits1(String inputString) throws Exception {
        int cnt = 0;
        var splitRegex = ";|\\s|\n";
        var splitNumbers = inputString.split(splitRegex);
        for (var number : splitNumbers) {
            if (number.isEmpty()) {
                cnt += 0;
            } else {
                var parsedNumber = number.startsWith("$")
                        ? Integer.parseInt(number.substring(1), 16)
                        : Integer.parseInt(number);
                if (parsedNumber > UPPER_BOUND || parsedNumber < LOWER_BOUND) {
                    throw new NumberOutOfBoundsException("Number "+number+" is not inside the range ("+LOWER_BOUND+","+UPPER_BOUND+")");
                }
                cnt += Integer.bitCount(parsedNumber);
            }
        }
        return cnt;
    }

}
