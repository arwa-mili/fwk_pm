package flightService.servicee.Controller;


import flightService.servicee.Entity.FlightSearchRequest;
import flightService.servicee.Entity.FlightSearchResponse;
import flightService.servicee.Service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/search")
public class FlightSearchController {
    private final FlightSearchService flightSearchService;
    @GetMapping("/flights")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(
            @RequestBody FlightSearchRequest flightSearchRequest) {
        return new ResponseEntity<>(
                flightSearchService.searchFlights(flightSearchRequest), HttpStatus.OK);
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
