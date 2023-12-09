package flightService.servicee.Service;
import flightService.servicee.Entity.Flight;
import flightService.servicee.Entity.FlightSearchRequest;
import flightService.servicee.Entity.FlightSearchResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import flightService.servicee.Repository.FlightSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {
    @Autowired
    private FlightSearchRepository flightSearchRepository;


    private final MongoTemplate mongoTemplate;
    @Override
    public List<FlightSearchResponse> searchFlights(String origin, String destination, LocalDate departureDate, int passengers) {
        List<Flight> flights = flightSearchRepository.findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(
                origin, destination, departureDate, passengers);

        return flights.stream()
                .map(this::mapToFlightSearchResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightSearchRepository.findByFlightNumber(flightNumber);
    }

    @Override
    public List<Flight> searchAllFlights() {
        return flightSearchRepository.findAll();
    }



    @Override
    public void updateAvailableSeats(String flightNumber) {
        Query query = new Query(Criteria.where("flightNumber").is(flightNumber));
        Update update = new Update().inc("availableSeats", -1);
        mongoTemplate.updateFirst(query, update, Flight.class);
    }


    /*@Override
    public void updateAvailableSeats(String flightNumber) {
        Flight flight = flightSearchRepository.findByFlightNumber(flightNumber);

        if (flight != null) {
            int availableSeats = flight.getAvailableSeats() - 1;
            flight.setAvailableSeats(availableSeats);
            flightSearchRepository.save(flight);
        }
    }*/


/*
    @Override
    public void addFlight(FlightSearchRequest flightSearchRequest) {
        // Convert FlightSearchRequest to Flight and save to MongoDB
        Flight flight = new Flight(
                null,
                null, // flightNumber is not available in FlightSearchRequest
                flightSearchRequest2.getOrigin(),
                flightSearchRequest2.getDestination(),
                flightSearchRequest2.getTravelDate(),
                null, // arrivalDate is not available in FlightSearchRequest
                flightSearchRequest2.getAmount(),
                flightSearchRequest2.getTotalSeats(),
                flightSearchRequest2.getPassengers());

        flightSearchRepository.save(flight);
    }

    */


    private FlightSearchResponse mapToFlightSearchResponse(Flight flight) {
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        BeanUtils.copyProperties(flight, flightSearchResponse);
        return flightSearchResponse;
    }



}
