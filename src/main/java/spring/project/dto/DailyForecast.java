package spring.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyForecast {

    @JsonProperty("day_name")
    private String dayName;

    @JsonProperty("temp_high_celsius")
    private double tempHighCelsius;

    @JsonProperty("forecast_blurp")
    private String forecastBlurp;
}