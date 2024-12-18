package org.poo.service;

import org.poo.exception.NoExchangeRateException;
import org.poo.fileio.ExchangeInput;
import org.poo.model.exchange.CurrencyExchangeRate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa finală ExchangeService gestionează operațiunile de conversie valutară
 */
public final class ExchangeService {
    private List<CurrencyExchangeRate> exchangeRates = new ArrayList<>();

    /**
     * Încarcă ratele de schimb valutar dintr-o listă de intrări
     * @param exchangeInputs este Lista de obiecte ExchangeInput care conțin
     * datele pentru ratele de schimb
     */
    public void loadExchangeRates(final List<ExchangeInput> exchangeInputs) {
        for (ExchangeInput input : exchangeInputs) {
            exchangeRates.add(new CurrencyExchangeRate(
                    input.getFrom(), input.getTo(), input.getRate()));
        }
    }

    /**
     * Convertește o sumă dintr-o monedă în alta, utilizând ratele de schimb valutar disponibile
     * Dacă nu există o rată directă între cele două monede, încearcă să găsească o cale
     * intermediară
     * prin alte monede disponibile.
     * @param fromCurrency este moneda din care se face conversia
     * @param toCurrency este moneda în care se face conversia
     * @param amount este suma de convertit
     * @return suma convertită în moneda dorită
     * @throws NoExchangeRateException dacă nu există o rată de schimb valutar disponibilă
     * între cele două monede
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
