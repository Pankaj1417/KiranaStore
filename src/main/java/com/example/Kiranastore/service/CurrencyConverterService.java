package com.example.Kiranastore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {
    @Value("${currency-conversion-api.url}")
    private String currencyConversionApiUrl;

    @Value("${currency-conversion-api.api-key}")
    private String apiKey;

    public double convertAmount(double amount, String fromCurrency, String toCurrency) {
        return amount * getConversionRate(fromCurrency, toCurrency);
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        return (fromCurrency.equals("USD") && toCurrency.equals("INR")) ? 75 : 1 / 75.0;
    }
}