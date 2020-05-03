package Primdahl.Mandatory3.Model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeatherEntry {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private Instant timestamp;
    private double temperature;
    private Integer weatherId;
    private String weatherIcon;
    private String description;
    private double windSpeed;
    private int humidity;
    private int visibility;

    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return this.timestamp;
    }

    public String getFormattedTime(){
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.UK)
                        .withZone(ZoneId.systemDefault());


        DayOfWeek dayOfWeek = this.timestamp.atZone(ZoneId.systemDefault()).getDayOfWeek();

        LocalDate currentDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate();
        LocalDate weatherDate = LocalDateTime.ofInstant(this.timestamp, ZoneId.systemDefault()).toLocalDate();

        if (currentDate.equals(weatherDate)){
            return "Today "+formatter.format(this.timestamp);
        }
        return dayOfWeek.toString().substring(0, 1).toUpperCase()+
                dayOfWeek.toString().substring(1).toLowerCase()+" "+
                formatter.format(this.timestamp);
    }

    @JsonSetter("dt")
    public void setTimestamp(long unixTime) {
        this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
    }

    @JsonSetter("visibility")
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("visibility")
    public int getVisibility() {
        return visibility;
    }

    // Return the temperature in Kelvin (K).
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
        setHumidity(Integer.parseInt(main.get("humidity").toString()));
    }

    @JsonProperty("wind")
    public void setWind(Map<String, Object> wind) {
        setWind(Double.parseDouble(wind.get("speed").toString()));
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setWind(double wind) {
        this.windSpeed = wind;
    }


    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get("id"));
        setWeatherIcon((String) weather.get("icon"));
        setDescription((String) weather.get("description"));
    }

    public String getCelsiusTemperature() {
        double celsiusTemp = this.temperature - 273.15;
        return String.format("%4.1f", celsiusTemp);
    }
}
