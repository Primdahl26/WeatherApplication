package Primdahl.Mandatory3;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.weather")
public class WeatherAppProperties {

    // Requires the API key to be valid for the program to run
    @Valid
    private final Api api = new Api();

    // The different locations that is given in the application.properties file
    private List<String> locations;
    public Api getApi() {
        return this.api;
    }
    public List<String> getLocations() {
        return this.locations;
    }
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public static class Api {
        // API key of the OpenWeatherMap service
        @NotNull
        private String key;
        public String getKey() {
            return this.key;
        }
        public void setKey(String key) {
            this.key = key;
        }
    }
}