package flightService.service.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewFlightEvent {


    Long id;
    String flightNumber;
    String origin;
    String destination;
    LocalDate departureDate;
    LocalDate arrivalDate;
    double amount;
    int totalSeats;
    int availableSeats;

}
