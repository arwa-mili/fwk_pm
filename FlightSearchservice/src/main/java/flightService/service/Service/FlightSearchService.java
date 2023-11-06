package flightService.service.Service;

import flightService.service.Entity.FlightSearchRequest;
import flightService.service.Entity.FlightSearchResponse;

import java.util.List;

public interface FlightSearchService {
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest);
}