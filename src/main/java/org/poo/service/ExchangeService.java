package org.poo.service;

import org.poo.exception.NoExchangeRateException;
import org.poo.fileio.ExchangeInput;
import org.poo.model.exchange.CurrencyExchangeRate;
import java.util.ArrayList;
import java.util.List;

public final class ExchangeService {
    private List<CurrencyExchangeRate> exchangeRates = new ArrayList<>();

    /**
     *
     * @param exchangeInputs
     */
    public void loadExchangeRates(final List<ExchangeInput> exchangeInputs) {
        for (ExchangeInput input : exchangeInputs) {
            exchangeRates.add(new CurrencyExchangeRate(
                    input.getFrom(), input.getTo(), input.getRate()
            ));
        }
    }

    /**
     *
     * @param fromCurrency
     * @param toCurrency
     * @param amount
     * @return
     */
    public double convertCurrency(final String fromCurrency, final String toCurrency,
                                  final double amount) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            return amount;
        }

        for (CurrencyExchangeRate rate : exchangeRates) {
            if (rate.getFromCurrency().equalsIgnoreCase(fromCurrency)
                    && rate.getToCurrency().equalsIgnoreCase(toCurrency)) {
                return amount * rate.getRate();
            }
            if (rate.getFromCurrency().equalsIgnoreCase(toCurrency)
                    && rate.getToCurrency().equalsIgnoreCase(fromCurrency)) {
                return amount / rate.getRate();
            }
        }

        for (CurrencyExchangeRate rate : exchangeRates) {
            if (rate.getFromCurrency().equalsIgnoreCase(fromCurrency)) {
                double intermediateAmount = amount * rate.getRate();
                return convertCurrency(rate.getToCurrency(), toCurrency, intermediateAmount);
            }
            if (rate.getToCurrency().equalsIgnoreCase(fromCurrency)) {
                double intermediateAmount = amount / rate.getRate();
                return convertCurrency(rate.getFromCurrency(), toCurrency, intermediateAmount);
            }
        }
        throw new NoExchangeRateException("No exchange rate found for "
                + fromCurrency + " to " + toCurrency);
    }
}
