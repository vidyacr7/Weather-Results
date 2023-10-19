package spring.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.dto.DailyForecast;
@Data
@NoArgsConstructor
public class Weather {

    @JsonProperty("daily")
    private DailyForecast daily;
}