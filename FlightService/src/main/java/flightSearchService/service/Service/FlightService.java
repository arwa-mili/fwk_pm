package flightSearchService.service.Service;

import flightSearchService.service.Entity.FlightRequest;
import flightSearchService.service.Entity.FlightResponse;

import java.util.List;



public interface FlightService {

    FlightResponse createFlight(FlightRequest flightRequest);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightByNumber(String flightNumber);

}