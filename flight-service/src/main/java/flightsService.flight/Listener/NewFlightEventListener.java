package flightsService.flight.Listener;


import flightsService.flight.Event.NewFlightEvent;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewFlightEventListener {

    private final KafkaTemplate<String, NewFlightEvent> kafkaTemplate;
    private final ObservationRegistry observationRegistry;

    @EventListener
    public void handleOrderPlacedEvent(NewFlightEvent event) {
        log.info("  Event Received, Sending to notificationTopic: {}", event.getId(),event.getFlightNumber(),event.getOrigin(),
                event.getDestination(),event.getDepartureDate(),event.getArrivalDate(),event.getAmount(),event.getTotalSeats(),event.getAvailableSeats());

        // Create Observation for Kafka Template
        try {
            Observation.createNotStarted("notification-topic", this.observationRegistry).observeChecked(() -> {
                CompletableFuture<SendResult<String, NewFlightEvent>> future = kafkaTemplate.send("notificationTopic",
                        new NewFlightEvent(event.getId(),event.getFlightNumber(),event.getOrigin(),
                                event.getDestination(),event.getDepartureDate(),event.getArrivalDate(),event.getAmount(),event.getTotalSeats(),event.getAvailableSeats()));
                return future.handle((result, throwable) -> CompletableFuture.completedFuture(result));
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while sending message to Kafka", e);
        }
    }
}