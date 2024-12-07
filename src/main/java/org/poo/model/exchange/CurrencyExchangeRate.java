package org.poo.model.exchange;

public class CurrencyExchangeRate {
    private String fromCurrency;
    private String toCurrency;
    private double rate;

    public CurrencyExchangeRate(String fromCurrency, String toCurrency, double rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getRate() {
        return rate;
    }
}
