package spring.project.dto;

import org.junit.jupiter.api.Test;
import spring.project.dto.DailyForecast;
import spring.project.dto.Weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WeatherTest {

    @Test
    public void testGetSetDaily() {
        DailyForecast dailyForecasts = new DailyForecast();

        Weather weather = new Weather();
        assertNull(weather.getDaily());

        weather.setDaily(dailyForecasts);
        assertEquals(dailyForecasts, weather.getDaily());
    }
}