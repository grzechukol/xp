package com.calculations;

import com.jsonUsage.JsonConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumUpCalculator {
    private List<String[]> transactions;

    public SumUpCalculator(List<String[]> transactions) {
        this.transactions = transactions;
    }

    public Map<String, Double> getSummaryMap() {
        Map<String, Double> summary = new HashMap<>();

        for (String[] transaction : this.transactions) {
            var name = transaction[1];
            var currency = transaction[4];
            var baseCurrency = JsonConfig.getCurrency();
            var price = Double.parseDouble(transaction[5]);

            var isCurrencyBase = currency.equals(baseCurrency);
            var isConvertible = CurrencyConverter.isConvertible(currency, baseCurrency);

            var key = "";
            if (isCurrencyBase || !isConvertible) {
                key = name + ":" + currency;
            } else {
                key = name + ":" + baseCurrency;
                price = CurrencyConverter.convert(currency, baseCurrency, price);
            }

            if (summary.containsKey(key)) {
                summary.put(key, summary.get(key)+price);
            } else {
                summary.put(key, price);
            }
        }

        return summary;
    }

    public void printSummary() {
        var summary = getSummaryMap();

        for (var entry : summary.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            var splitKey = key.split(":");
            var name = splitKey[0];
            var currency = splitKey[1];

            if (value > 0) {
                System.out.println(name + " owes you " + value + " " + currency);
            } else {
                System.out.println("You owe "+ name + " " + Math.abs(value) + " " + currency);
            }
        }

    }
}
