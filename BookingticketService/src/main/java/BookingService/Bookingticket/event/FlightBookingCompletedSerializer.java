package BookingService.Bookingticket.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class FlightBookingCompletedSerializer implements Serializer<FlightBookingCompleted> {
    @Override
    public byte[] serialize(String topic, FlightBookingCompleted data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing FlightBookingCompleted", e);
        }
    }
}
