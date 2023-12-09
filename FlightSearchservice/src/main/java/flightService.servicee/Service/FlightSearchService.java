package flightService.servicee.Service;



import flightService.servicee.Entity.Flight;
import flightService.servicee.Entity.FlightSearchRequest;
import flightService.servicee.Entity.FlightSearchResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface FlightSearchService {

    public List<FlightSearchResponse> searchFlights(String origin, String destination, LocalDate departureDate, int passengers);



    public Flight getFlightByFlightNumber(String flightNumber);

    public List<Flight> searchAllFlights();

    void updateAvailableSeats(String flightNumber);

    //void handleNewFlightNotification(FlightResponse flightResponse);


    //void addFlight(FlightSearchRequest flightSearchRequest);



}