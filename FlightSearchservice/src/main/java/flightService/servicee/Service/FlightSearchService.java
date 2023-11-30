package flightService.servicee.Service;



import flightService.servicee.Entity.FlightSearchRequest;
import flightService.servicee.Entity.FlightSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FlightSearchService {

    public List<FlightSearchResponse> searchFlights(FlightSearchRequest flightSearchRequest);

    //void handleNewFlightNotification(FlightResponse flightResponse);


    //void addFlight(FlightSearchRequest flightSearchRequest);



}