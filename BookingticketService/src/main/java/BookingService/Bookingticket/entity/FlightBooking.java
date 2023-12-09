package BookingService.Bookingticket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("FLIGHT")
public class FlightBooking extends Booking {

    private String flightNumber;
    private String destination;
}