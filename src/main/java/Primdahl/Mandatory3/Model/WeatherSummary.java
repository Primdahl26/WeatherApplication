package Primdahl.Mandatory3.Model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class WeatherSummary {

    private String country;
    private String city;
    private Integer code;
    private String icon;
    private String description;
    private double temperature;
    private double windSpeed;
    private int humidity;
    private int visibility;
    private Instant timestamp;

    public WeatherSummary(String country, String city, Weather weather) {
        this.country = country;
        this.city = city;
        this.description = weather.getDescription();
        this.code = weather.getWeatherId();
        this.icon = weather.getWeatherIcon();
        this.temperature = weather.getTemperature();
        this.windSpeed = weather.getWindSpeed();
        this.humidity = weather.getHumidity();
        this.visibility = weather.getVisibility();
        this.timestamp = weather.getTimestamp();
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getIcon() {
        return this.icon;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return this.windSpeed;
    }

    public int getVisibility() {
        return visibility;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getCelsiusTemperature() {
        double celsiusTemp = this.temperature - 273.15;
        return String.format("%4.1f", celsiusTemp);
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

}