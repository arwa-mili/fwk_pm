package flightSearchService.service.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
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