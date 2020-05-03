package Primdahl.Mandatory3.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Primdahl.Mandatory3.Service.WeatherService;
import Primdahl.Mandatory3.Model.Weather;
import Primdahl.Mandatory3.Model.WeatherSummary;
import Primdahl.Mandatory3.WeatherAppProperties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WeatherSummaryController {

    private final WeatherService weatherService;
    private final WeatherAppProperties properties;

    public WeatherSummaryController(WeatherService weatherService, WeatherAppProperties properties) {
        this.weatherService = weatherService;
        this.properties = properties;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView conferenceWeather() {
        Map<String, Object> model = new LinkedHashMap<>();
        model.put("summary", getSummary());

        return new ModelAndView("summary", model);
    }

    private Object getSummary() {
        List<WeatherSummary> summary = new ArrayList<>();
        for (String location : this.properties.getLocations()) {
            String country = location.split("/")[0];
            String city = location.split("/")[1];
            Weather weather = this.weatherService.getWeather(country, city);
            summary.add(createWeatherSummary(country, city, weather));

            weatherService.create(createWeatherSummary(country, city, weather));
        }
        return summary;
    }

    private WeatherSummary createWeatherSummary(String country, String city,
                                                Weather weather) {

        return new WeatherSummary(country, city, weather);
    }
}