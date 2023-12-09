package BookingService.Bookingticket.Listener;

import BookingService.Bookingticket.event.FlightBookingCompleted;
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
public class FlightBookingCompletedListener {

    private final KafkaTemplate<String, FlightBookingCompleted> kafkaTemplate;
    private final ObservationRegistry observationRegistry;

    @EventListener
    public void handleFlightCreatedEvent(FlightBookingCompleted event) {
        log.info("  Event Received, Sending to bookingnotificationTopic: {}", event.getBookingNumber());



        try {


            Observation.createNotStarted("notification-topic", this.observationRegistry).observeChecked(() -> {
                CompletableFuture<SendResult<String, FlightBookingCompleted>> future = kafkaTemplate.send("bookingnotificationTopic",
                       new FlightBookingCompleted(event.getBookingNumber()) );
                return future.handle((result, throwable) -> CompletableFuture.completedFuture(result));
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while sending message to Kafka", e);
        }
        // Create Observation for Kafka Template

    }
}
