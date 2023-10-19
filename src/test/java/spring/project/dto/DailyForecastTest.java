package spring.project.dto;

import org.junit.jupiter.api.Test;
import spring.project.dto.DailyForecast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DailyForecastTest {

    @Test
    public void testGetSetDayName() {
        String dayName = "Monday";

        DailyForecast dailyForecast = new DailyForecast();
        assertNull(dailyForecast.getDayName());

        dailyForecast.setDayName(dayName);
        assertEquals(dayName, dailyForecast.getDayName());
    }

    @Test
    public void testGetSetTempHighCelsius() {
        double tempHighCelsius = 27.2;

        DailyForecast dailyForecast = new DailyForecast();
        assertEquals(0.0, dailyForecast.getTempHighCelsius());

        dailyForecast.setTempHighCelsius(tempHighCelsius);
        assertEquals(tempHighCelsius, dailyForecast.getTempHighCelsius());
    }

    @Test
    public void testGetSetForecastBlurp() {
        String forecastBlurp = "Partly Sunny";

        DailyForecast dailyForecast = new DailyForecast();
        assertNull(dailyForecast.getForecastBlurp());

        dailyForecast.setForecastBlurp(forecastBlurp);
        assertEquals(forecastBlurp, dailyForecast.getForecastBlurp());
    }
}