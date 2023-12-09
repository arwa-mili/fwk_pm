package flightsService.flight.Controller;



import flightsService.flight.Entity.FlightRequest;
import flightsService.flight.Entity.FlightResponse;
import flightsService.flight.Service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest) {

        var flight = flightService.createFlight(flightRequest);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public FlightResponse getFlightByNumber(@PathVariable("id") String flightNumber) {
        return flightService.getFlightByNumber(flightNumber);
    }

}