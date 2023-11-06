package flightService.service.Repository;

import flightService.service.Entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightSearchRepository extends MongoRepository<Flight, Long> {

    List<Flight> findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(
            String origin,
            String destination, LocalDate date, int passengers);
}
