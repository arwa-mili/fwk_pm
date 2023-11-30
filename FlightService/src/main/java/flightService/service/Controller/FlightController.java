package flightService.service.Controller;


import flightService.service.Entity.FlightRequest;
import flightService.service.Entity.FlightResponse;
import flightService.service.Service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
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