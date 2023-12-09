package flightService.servicee.Controller;


import flightService.servicee.Entity.Flight;
import flightService.servicee.Entity.FlightSearchRequest;
import flightService.servicee.Entity.FlightSearchResponse;
import flightService.servicee.Service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/search")
public class FlightSearchController {
    private final FlightSearchService flightSearchService;
    @GetMapping("/flights")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam int passengers) {

        List<FlightSearchResponse> foundFlights = flightSearchService.searchFlights(origin, destination, departureDate, passengers);

        if (foundFlights.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(foundFlights);
        }
    }


    @GetMapping("/getall")
    public ResponseEntity<List<Flight>> searchFlights() {
        return new ResponseEntity<>(
                flightSearchService.searchAllFlights(), HttpStatus.OK);
    }
    @PutMapping("/updateSeats/{flightNumber}")
    public ResponseEntity<String> updateAvailableSeats(
            @PathVariable String flightNumber) {

        flightSearchService.updateAvailableSeats(flightNumber);

        return ResponseEntity.ok("Available seats updated successfully.");
    }








    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable String flightNumber) {
        Flight flight = flightSearchService.getFlightByFlightNumber(flightNumber);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }



/*
    @PostMapping("/flights")
    public ResponseEntity<Void> addFlight(@RequestBody FlightSearchRequest flightSearchRequest) {
        flightSearchService.addFlight(flightSearchRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/notify")
    public ResponseEntity<Void> notifyNewFlight(@RequestBody FlightResponse flightResponse) {
        flightSearchService.handleNewFlightNotification(flightResponse);
        return ResponseEntity.ok().build();
    }*/
}
