package org.poo.service;

import org.poo.fileio.ExchangeInput;
import org.poo.model.exchange.CurrencyExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class ExchangeService {
    private List<CurrencyExchangeRate> exchangeRates = new ArrayList<>();

    public void loadExchangeRates(List<ExchangeInput> exchangeInputs) {
        for (ExchangeInput input : exchangeInputs) {
            exchangeRates.add(new CurrencyExchangeRate(
                    input.getFrom(), input.getTo(), input.getRate()
            ));
        }
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            return amount;
        }
        for (CurrencyExchangeRate rate : exchangeRates) {
            if (rate.getFromCurrency().equalsIgnoreCase(fromCurrency)
                    && rate.getToCurrency().equalsIgnoreCase(toCurrency)) {
                return amount * rate.getRate();
            }
        }
        //throw new IllegalArgumentException("No exchange rate found for " + fromCurrency + " to " + toCurrency);
        return amount;
    }
}
