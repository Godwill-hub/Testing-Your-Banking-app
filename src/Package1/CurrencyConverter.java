package Package1;
import java.util.logging.Logger;
import java.time.LocalDateTime;

public class CurrencyConverter {
    private static final Logger logger = Logger.getLogger(CurrencyConverter.class.getName());
    private ExchangeRateService exchangeRateService;

    public CurrencyConverter(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public double convert(String fromCurrency, String toCurrency, double amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (!isValidCurrencyCode(fromCurrency) || !isValidCurrencyCode(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code.");
        }

        double exchangeRate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate <= 0) {
            throw new Exception("Invalid exchange rate.");
        }

        double convertedAmount = amount * exchangeRate;
        logTransaction(fromCurrency, toCurrency, amount, exchangeRate, convertedAmount);
        return convertedAmount;
    }

    private boolean isValidCurrencyCode(String currencyCode) {
        return currencyCode.matches("[A-Z]{3}");
    }

    private void logTransaction(String fromCurrency, String toCurrency, double amount, double exchangeRate, double convertedAmount) {
        String logMessage = String.format("Transaction: %s -> %s, Amount: %.2f, Rate: %.4f, Converted: %.2f, Time: %s",
                fromCurrency, toCurrency, amount, exchangeRate, convertedAmount, LocalDateTime.now());
        logger.info(logMessage);
    }
}
