package TDD;

public class BitCounter {
    public static class OutOfBoundsException extends Exception {
        public OutOfBoundsException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class WrongFormatException extends Exception {
        public WrongFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    public int noOfBits1(String numbers) {
        return 1;
    }
}
