package Primdahl.Mandatory3.Controller;

import Primdahl.Mandatory3.Service.WeatherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WeatherApiController {

    private final WeatherService weatherService;

    public WeatherApiController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/now/{country}/{city}")
    public ModelAndView getWeather(@PathVariable String country,
                              @PathVariable String city) {

        Map<String, Object> model = new LinkedHashMap<>();
        model.put("now", this.weatherService.getWeather(country, city));

        return new ModelAndView("now", model);
    }

    @RequestMapping("/weekly/{country}/{city}")
    public ModelAndView getWeatherForecast(@PathVariable String country,
                                           @PathVariable String city) {

        Map<String, Object> model = new LinkedHashMap<>();
        model.put("weekly", this.weatherService.getWeatherForecast(country, city).getEntries());

        return new ModelAndView("weekly", model);
    }
}