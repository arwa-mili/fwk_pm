package flightService.servicee;

import flightService.servicee.Entity.Flight;
import flightService.servicee.Entity.FlightSearchRequest2;
import flightService.servicee.Repository.FlightSearchRepository;
import flightService.servicee.kafka.NewFlightEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
@EnableDiscoveryClient
public class FlightSearchServiceApplication {
	@Autowired
	private FlightSearchRepository flightSearchRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchServiceApplication.class, args);
	}


	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(NewFlightEvent newFlightEvent){


		Flight f =new Flight(
				newFlightEvent.getId(),newFlightEvent.getFlightNumber(),newFlightEvent.getOrigin(),
				newFlightEvent.getDestination(),newFlightEvent.getDepartureDate(),newFlightEvent.getArrivalDate(),newFlightEvent.getAmount(),
				newFlightEvent.getTotalSeats(),newFlightEvent.getAvailableSeats()
			);

		flightSearchRepository.save(f);







		log.info("Recieved notification for order - {} -{} -{} -{} -{} - {} -{} -{} -{}",newFlightEvent.getId(),newFlightEvent.getFlightNumber(),newFlightEvent.getOrigin(),
				newFlightEvent.getDestination(),newFlightEvent.getDepartureDate(),newFlightEvent.getArrivalDate(),newFlightEvent.getAmount(),
				newFlightEvent.getTotalSeats(),newFlightEvent.getAvailableSeats());


	}


}
