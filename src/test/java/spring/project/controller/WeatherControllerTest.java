
package spring.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import spring.project.dto.Weather;
import spring.project.service.WeatherService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(new WeatherController(weatherService)).build();
    }

    @Test
    public void testGetCurrentWeather() {
        List<Weather> weather = Collections.singletonList(new Weather());
        // Set up the weather object with the desired data

        when(weatherService.getCurrentWeather()).thenReturn(Mono.just(weather));

        webTestClient.get().uri("/weather/current")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Weather>>() {
                })
                .isEqualTo(weather);
    }
}
