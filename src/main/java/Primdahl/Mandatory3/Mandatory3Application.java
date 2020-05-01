package Primdahl.Mandatory3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties(WeatherAppProperties.class)
@EnableCaching(proxyTargetClass = true)
public class Mandatory3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mandatory3Application.class, args);
	}
}
