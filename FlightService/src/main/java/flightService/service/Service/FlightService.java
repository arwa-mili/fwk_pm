package flightService.service.Service;

import flightService.service.Entity.FlightRequest;
import flightService.service.Entity.FlightResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FlightService {

    FlightResponse createFlight(FlightRequest flightRequest);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightByNumber(String flightNumber);

}