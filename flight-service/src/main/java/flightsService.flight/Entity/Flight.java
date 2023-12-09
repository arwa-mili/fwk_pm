package flightsService.flight.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Flights")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    @Column(unique = true)
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private int totalSeats;
    private int availableSeats;
    private double amount;
}




/*{

        "flightId":1,
        "flightNumber":12,
        "origin": "Monastir",
        "destination" :"Tunis",
        "departureDate" : 5,
        "arrivalDate" : 6,
        "totalSeats" : 15,
        "availableSeats" : 8,
        "amount": 45
        }
 */