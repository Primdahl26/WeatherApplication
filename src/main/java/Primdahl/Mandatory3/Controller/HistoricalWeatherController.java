package Primdahl.Mandatory3.Controller;

import Primdahl.Mandatory3.Service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HistoricalWeatherController {

    //TODO: Make historical searchable
    //TODO: Make WeatherForecasts historical too

    private final WeatherService weatherService;

    public HistoricalWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/historical")
    public ModelAndView getHistoricalWeather(){

        Map<String, Object> model = new LinkedHashMap<>();
        model.put("historical", this.weatherService.getWeatherSummaryList());

        return new ModelAndView("historical", model);
    }
}
