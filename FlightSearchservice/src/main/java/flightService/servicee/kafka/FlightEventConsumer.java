package flightService.servicee.kafka;

/*
import flightService.servicee.Entity.FlightCreatedEvent;
import flightService.servicee.Service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightEventConsumer {

    private final FlightSearchService flightSearchService;

    @KafkaListener(topics = "flight-created-topic", groupId = "flight-search-service-group")
    public void handleFlightCreatedEvent(FlightCreatedEvent flightCreatedEvent) {
        // Save the received flight information to the FlightSearchService database
        flightSearchService.saveFlightInformation(flightCreatedEvent.flight());
    }
}

*/