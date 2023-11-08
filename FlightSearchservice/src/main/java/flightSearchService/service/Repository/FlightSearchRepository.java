package flightSearchService.service.Repository;

import flightSearchService.service.Entity.Flight;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository

public interface FlightSearchRepository extends MongoRepository<Flight, Long> {

    List<Flight> findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(
            String origin,
            String destination, LocalDate date, int passengers);
}
