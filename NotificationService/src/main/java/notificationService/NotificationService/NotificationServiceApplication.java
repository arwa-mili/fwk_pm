package notificationService.NotificationService;

import lombok.extern.slf4j.Slf4j;
import notificationService.NotificationService.event.FlightBookingCompleted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
@EnableDiscoveryClient
public class NotificationServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);



	}

	@KafkaListener(topics = "bookingnotificationTopic")
	public void handleBookingnotification(FlightBookingCompleted flightBookingCompleted){

		try {
			log.info("Received notification for - {}", flightBookingCompleted.getBookingNumber());
			logger.info(flightBookingCompleted.getBookingNumber());
		} catch (Exception e) {
			log.error("Error handling Kafka message", e);
		}



}


}
