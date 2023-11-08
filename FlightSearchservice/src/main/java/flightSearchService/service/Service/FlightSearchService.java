package flightSearchService.service.Service;

import flightSearchService.service.Entity.FlightSearchRequest;
import flightSearchService.service.Entity.FlightSearchResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FlightSearchService {

    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest);
}