package flightService.servicee.Entity;



import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "flights")
public record Flight(
        @Id Long id,
        String flightNumber,
        String origin,
        String destination,
        LocalDate departureDate,
        LocalDate arrivalDate,
        double amount,
        int totalSeats,
        int availableSeats) {

    public Flight withAvailableSeats(int newAvailableSeats) {
        return new Flight(id, flightNumber, origin, destination, departureDate, arrivalDate, amount, totalSeats, newAvailableSeats);
    }



}