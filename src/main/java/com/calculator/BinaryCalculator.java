package com.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryCalculator {
    public BinaryCalculator() { }

    public int noOfBits1(String stringNumbers) throws Exception {
        var splitRegEx = ";|\\s+";
        String[] removedEmpty;
        List<Integer> arrayOfInts = new ArrayList<>(Arrays.asList());
            removedEmpty = Arrays.stream(stringNumbers.split(splitRegEx, -1))
                    .filter(item -> !item.isEmpty())
                    .toArray(String[]::new);

        try {
            for (String number: removedEmpty) {
                if(number.startsWith("$")) {
                    number = number.replace("$", "");
                    arrayOfInts.add(Integer.parseInt(number, 16));
                } else {
                    arrayOfInts.add(Integer.parseInt(number));
                }
            }
        } catch (Exception e) {
            throw new Exception("Cannot parse numbers");
        }

        var outOfRange = arrayOfInts.stream()
                .mapToInt(i -> i)
                .filter(x -> x < 0 || x > 255)
                .toArray();

        if(Arrays.stream(outOfRange).findAny().isPresent()) {
            throw new Exception("Number is out of bounds");
        }

        var numberOf1s = 0;
        for (var number: arrayOfInts) {
            var binary = Integer.toBinaryString(number);
            numberOf1s += (int) binary.chars().filter(c -> c == '1').count();
        }

        return numberOf1s;
    }
}
