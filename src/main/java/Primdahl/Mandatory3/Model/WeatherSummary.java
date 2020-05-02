package Primdahl.Mandatory3.Model;

import java.time.Instant;

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
    private Instant timeStamp;

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
        this.timeStamp = weather.getTimestamp();
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

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public String getCelsiusTemperature() {
        double celsiusTemp = this.temperature - 273.15;
        return String.format("%4.1f", celsiusTemp);
    }
}