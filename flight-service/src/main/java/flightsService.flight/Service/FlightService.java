package flightsService.flight.Service;


import flightsService.flight.Entity.FlightRequest;
import flightsService.flight.Entity.FlightResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FlightService {

    FlightResponse createFlight(FlightRequest flightRequest);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightByNumber(String flightNumber);

}