package spring.project.controller;

import spring.project.dto.Weather;
import spring.project.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * This Controller will call the weather service call
     * @return Mono<List<Weather>>
     */
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Weather>> getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }
}