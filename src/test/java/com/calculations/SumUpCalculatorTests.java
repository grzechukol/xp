package com.calculations;

import com.jsonUsage.JsonConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumUpCalculatorTests {

    @Test
    void getSummaryMap_ShouldReturnCorrectMap(){

        // arrange
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("Transaction1:EUR", 10.0);
        expectedResult.put("Transaction2:USD", 2.0);

        var transactions = new ArrayList<String[]>();
        // "ID", "Name", "Description", "Date", "Currency", "Price"
        transactions.add(new String[]{ "1", "Transaction1","Transaction1 Description","10-05-2021","EUR", "10" });
        transactions.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD", "-3" });
        transactions.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD", "5" });

        var sut = new SumUpCalculator(transactions);
        // act
        var result = sut.getSummaryMap();
        // assert
        assertEquals(expectedResult, result);
    }

}
