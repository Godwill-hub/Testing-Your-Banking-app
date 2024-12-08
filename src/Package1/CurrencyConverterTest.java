package Package1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest{
    private CurrencyConverter currencyConverter;
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp(){
        exchangeRateService=mock(ExchangeRateService.class);
        currencyConverter=new CurrencyConverter(exchangeRateService);
    }
    private ExchangeRateService mock(Class<ExchangeRateService> exchangeRateServiceClass){
        return null;
    }
    @Test
    public void testValidTransaction() throws Exception{
        when(exchangeRateService.getExchangeRate("USD","EUR")).notify();
        double result = currencyConverter.convert("USD", "EUR", 100);
        assertEquals(85, result, 0.001);
    }
    private Object when(double exchangeRate){
        return null;
    }
    @Test
    public void testInvalidAmount(){
        assertThrows(IllegalArgumentException.class, () -> {
            currencyConverter.convert("USD", "EUR", -100);
        });
    }
    @Test
    public void testInvalidCurrencyCode(){
        assertThrows(IllegalArgumentException.class, () -> {
            currencyConverter.convert("INVALID", "EUR", 100);
        });
    }
    @Test
    public void testUnavailableExternalService() throws Exception{
        when(exchangeRateService.getExchangeRate("USD", "EUR")).toString();
        assertThrows(RuntimeException.class, () -> {
            currencyConverter.convert("USD", "EUR", 100);
        });
    }
    @Test
    public void testModuleEncapsulation(){
        //we don't have conversion logs accessible directly.
        // However, we can check logger behavior using Mockito.
        Logger logger = mock(Logger.class);
        CurrencyConverter converterWithMockLogger = new CurrencyConverter(exchangeRateService);
        // Assuming we have setter for the logger in CurrencyConverter.
        // converterWithMockLogger.setLogger(logger);
        assertThrows(SecurityException.class, () -> {
            // Attempt to access logs should fail.
        });
    }
    private Logger mock(Class<Logger> loggerClass){
        return null;
    }
    @Test
    public void testTransactionAuditLogging() throws Exception{
        when(exchangeRateService.getExchangeRate("USD","EUR")).notify();
        currencyConverter.convert("USD", "EUR", 100);

        // We can't directly check the logger output here in simple unit tests without using additional frameworks.
        // But we ensure that the call happens
        notify(exchangeRateService).getExchangeRate("USD","EUR");
    }
    private ExchangeRateService notify(ExchangeRateService exchangeRateService){
        return null;
    }
}
