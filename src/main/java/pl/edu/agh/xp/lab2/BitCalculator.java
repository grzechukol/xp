package pl.edu.agh.xp.lab2;

import java.util.List;

public class BitCalculator {

    final int LOWER_BOUND = 0;
    final int UPPER_BOUND = 255;

    public int noOfBits1(String numbers) throws Exception {
        int bitCount;
        if (numbers.contains("$")){
            bitCount = Integer.parseInt(numbers,16);
        }else {


            String newNumbers = removeNumeric(numbers);

            int tmpNumberInt = Integer.parseInt(newNumbers);

            if (tmpNumberInt < 0 || tmpNumberInt > UPPER_BOUND) {
                throw new NumberOutOfBoundsException("Number " + newNumbers + " is not inside the range (" + LOWER_BOUND + "," + UPPER_BOUND + ")");
            }
            bitCount = Integer.bitCount(tmpNumberInt);

        }
        return bitCount;
    }

    public class NumberOutOfBoundsException extends Exception {
        public NumberOutOfBoundsException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class NumbersSeparatorException extends Exception{
        public NumbersSeparatorException(String errorMessage){
            super(errorMessage);
        }
    }

    public static String removeNumeric(String str)
    {
        return str.chars()
                .filter(ch -> Character.isDigit(ch))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
