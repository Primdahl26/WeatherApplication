package Primdahl.Mandatory3.Model;

import javax.persistence.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Entity
public class WeatherSummary {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
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

    public WeatherSummary(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Integer getCode() {
        return code;
    }

    public String getIcon() {
        return icon;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getVisibility() {
        return visibility;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Converts temperature to celsius
    public String getCelsiusTemperature() {
        double celsiusTemp = this.temperature - 273.15;
        return String.format("%4.1f", celsiusTemp);
    }

    // Formats time to: Dayname date time
    // Example: Monday 03/05/20 23:30
    public String getFormattedTime(){

        // Formats the instant timestamp to: date time
        // Example: 03/05/20 23:30
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.UK)
                        .withZone(ZoneId.systemDefault());


        // To compare, so we can see if the date is today
        DayOfWeek dayOfWeek = this.timestamp.atZone(ZoneId.systemDefault()).getDayOfWeek();

        LocalDate currentDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate();
        LocalDate weatherDate = LocalDateTime.ofInstant(this.timestamp, ZoneId.systemDefault()).toLocalDate();

        // If the date is today
        if (currentDate.equals(weatherDate)){
            return "Today "+formatter.format(this.timestamp);
        }

        // Makes it so the first letter of the word is uppercase and rest is lowercase
        return dayOfWeek.toString().substring(0, 1).toUpperCase()+
                dayOfWeek.toString().substring(1).toLowerCase()+" "+
                formatter.format(this.timestamp);
    }
}