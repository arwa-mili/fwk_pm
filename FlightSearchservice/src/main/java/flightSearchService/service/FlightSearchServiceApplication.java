package flightSearchService.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("flightSearchService.service.Repository")
@SpringBootApplication
public class FlightSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchServiceApplication.class, args);
	}

}
