package spring.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import spring.project.dto.DailyForecast;
import spring.project.dto.Periods;
import spring.project.dto.Weather;
import spring.project.model.WeatherFromApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    @Value("${forecast_url}")
    private String forecastUrl;
    private final WebClient webClient;

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<Weather>> getCurrentWeather() {
        return webClient.get()
                .uri(forecastUrl)
          //      .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherFromApi.class)
                .map(this::filter)
                .map(this::mapToWeather );
    }

    /**
     * This Method will filter today periods only from the list getting from forecast service
     * @return List<Periods>
     */
    private List<Periods> filter(WeatherFromApi weather) {
        // This will give us day name like Monday,Tuesday..etc
        return weather.getProperties().getPeriods().stream().filter(p -> p.getName().contains("Today") || p.getName().contains("Tonight"))
                .collect(Collectors.toList()); // filtering periods which are matching with today name
    }

    /**
     * This method take the data from the forecast service and transform into given format
     * @return List<Weather>
     */
    private List<Weather> mapToWeather(List<Periods> periods) {

        List<Weather> weathers = new ArrayList<>();
        for(Periods period : periods){
            Weather weather = new Weather();
            DailyForecast dailyForecast = new DailyForecast();
            dailyForecast.setDayName(period.getName());
            dailyForecast.setForecastBlurp(period.getShortForecast());
            dailyForecast.setTempHighCelsius(period.getTemperature());
            weather.setDaily(dailyForecast);

            weathers.add(weather);
        }
        return weathers;
    }
}