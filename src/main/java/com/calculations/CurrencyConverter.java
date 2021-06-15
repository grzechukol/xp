package com.calculations;

import com.jsonUsage.JsonConfig;
import com.jsonUsage.NoConverterException;

public class CurrencyConverter {
    public static boolean isConvertible(String fromCurrencyName, String toCurrencyName) {
        try {
            JsonConfig.getExchangeRate(fromCurrencyName, toCurrencyName);
            return true;
        } catch (NoConverterException e) {
            return false;
        }
    }

    public static Double convert(String fromCurrencyName, String toCurrencyName, Double value) {
        try {
            var rate = JsonConfig.getExchangeRate(fromCurrencyName, toCurrencyName);
            return rate*value;
        } catch (NoConverterException e) {
            return value;
        }
    }
}
