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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {
    @Autowired
    private FlightSearchRepository flightSearchRepository;
    @Override
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest) {
        List<Flight> flights = flightSearchRepository
                .findByOriginAndDestinationAndDepartureDateGreaterThanEqualAndAvailableSeatsGreaterThanEqual(
                        flightSearchRequest.origin(),
                        flightSearchRequest.destination(),
                        flightSearchRequest.travelDate(),
                        flightSearchRequest.passengers());

        List<FlightSearchResponse> flightSearchResponseList = flights
                .stream()
                .map(this::mapToFlightSearchResponse)
                .collect(Collectors.toList());

        return flightSearchResponseList;
    }


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
