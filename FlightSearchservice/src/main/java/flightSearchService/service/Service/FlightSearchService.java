package flightSearchService.service.Service;

import flightSearchService.service.Entity.FlightSearchRequest;
import flightSearchService.service.Entity.FlightSearchResponse;

import java.util.List;

public interface FlightSearchService {
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest);
}