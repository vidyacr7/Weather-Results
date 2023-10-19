package spring.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import spring.project.dto.DailyForecast;
import spring.project.dto.Properties;

@Data
public class WeatherFromApi {

    Properties properties;
}