package flightService.service.Controller;

import flightService.service.Entity.FlightSearchRequest;
import flightService.service.Entity.FlightSearchResponse;
import flightService.service.Service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
