
package spring.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import spring.project.dto.DailyForecast;
import spring.project.dto.Periods;
import spring.project.dto.Properties;
import spring.project.dto.Weather;
import spring.project.model.WeatherFromApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
public class WeatherServiceTest {

    @Mock
    private WebClient webClientMock;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    private WeatherService weatherService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(webClientMock);
       // ReflectionTestUtils.setField(weatherService,"baseUrl","https://api.weather.gov");
        ReflectionTestUtils.setField(weatherService,"forecastUrl","/gridpoints/MLB/33,70/forecast");
    }

    @Test
    public void testGetCurrentWeather() {

        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        Weather expectedWeather = new Weather();
        DailyForecast dailyForecast = new DailyForecast();
        dailyForecast.setDayName(dayName);
        dailyForecast.setTempHighCelsius(27.2);
        dailyForecast.setForecastBlurp("Partly Sunny");
        expectedWeather.setDaily(dailyForecast);

        WeatherFromApi weatherFromApi = new WeatherFromApi();
        Properties properties = new Properties();
        Periods periods = new Periods();
        periods.setName(dayName);
        periods.setTemperature(27.2);
        periods.setShortForecast("Partly Sunny");
        properties.setPeriods(List.of(periods));
        weatherFromApi.setProperties(properties);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<WeatherFromApi>>notNull())).thenReturn(Mono.just(weatherFromApi));

        Mono<List<Weather>> result = weatherService.getCurrentWeather();

        Assertions.assertEquals(dayName, result.block().get(0).getDaily().getDayName());
    }
}
