package org.poo.model.exchange;

public final class CurrencyExchangeRate {
    private final String fromCurrency;
    private final String toCurrency;
    private final double rate;

    public CurrencyExchangeRate(final String fromCurrency, final String toCurrency,
                                final double rate) {
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
