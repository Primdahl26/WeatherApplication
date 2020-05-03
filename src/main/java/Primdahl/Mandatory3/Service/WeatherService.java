package Primdahl.Mandatory3.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import Primdahl.Mandatory3.Model.Weather;
import Primdahl.Mandatory3.Model.WeatherForecast;
import Primdahl.Mandatory3.Model.WeatherSummary;
import Primdahl.Mandatory3.Repository.WeatherSummaryRepository;
import Primdahl.Mandatory3.WeatherAppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

//TODO: Comment

@Service
public class WeatherService {

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

    private static final String FORECAST_URL =
            "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;

    private final String apiKey;

    private final WeatherSummaryRepository weatherSummaryRepository;

    public WeatherService(RestTemplateBuilder restTemplateBuilder,
                          WeatherAppProperties properties, WeatherSummaryRepository weatherSummaryRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = properties.getApi().getKey();
        this.weatherSummaryRepository = weatherSummaryRepository;
    }

    public Weather getWeather(String country, String city) {
        logger.info("Requesting current weather for {}/{}", country, city);
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        return invoke(url, Weather.class);
    }

    public WeatherForecast getWeatherForecast(String country, String city) {
        logger.info("Requesting weather forecast for {}/{}", country, city);
        URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
        return invoke(url, WeatherForecast.class);
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }

    // Database part

    // Gets weather summary from the database
    public List<WeatherSummary> getWeatherSummaryList() {
        List<WeatherSummary> weatherSummaries = new ArrayList<>();
        weatherSummaryRepository.findAll().iterator().forEachRemaining(weatherSummaries::add);
        logger.info("Getting weather summaries from database: " +
                weatherSummaryRepository.findAll().toString());
        return weatherSummaries;
    }

    // Puts a weather summary to the database
    public void create(WeatherSummary weatherSummary) {
        weatherSummaryRepository.save(weatherSummary);
        logger.info("Putting weather summary to database: " + weatherSummary.toString());
    }

    // Deletes and item in the database (by ID)
    public void deleteById(Long id) {
        weatherSummaryRepository.delete(id);
    }
}
