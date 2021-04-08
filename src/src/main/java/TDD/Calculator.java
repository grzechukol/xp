package TDD;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static class NegativeNumbersException extends Exception {
        public NegativeNumbersException(String errorMessage) {
            super(errorMessage);
        }
    }

    public Integer add(String inputString) throws Exception {
        if (inputString == "") {
            return 0;
        }

        List<String> delimiters = new ArrayList<>();
        delimiters.add(",");
        delimiters.add("\n");

        if (inputString.startsWith("//"))
        {
            var splitOnFirstNewLine = inputString.split("\n",2);
            var customDelimiter = splitOnFirstNewLine[0].replace("//", "");
            delimiters.add(customDelimiter);
            inputString = splitOnFirstNewLine[1];
        }

        var delimiterRegex = String.join("|", delimiters);
        var splitNumbers = inputString
                .split(delimiterRegex);

        Integer cnt = 0;
        for (String number : splitNumbers) {
            var castedNumber = Integer.parseInt(number);

            if (castedNumber < 0) {
                throw new NegativeNumbersException("Negatives not allowed: " + castedNumber);
            }
            cnt += castedNumber;
        }
        return cnt;
    }
}
