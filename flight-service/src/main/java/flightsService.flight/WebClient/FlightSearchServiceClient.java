package flightsService.flight.WebClient;




import flightsService.flight.Entity.Flight;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class FlightSearchServiceClient {

    private final RestTemplate restTemplate;
    private final String flightSearchServiceUrl;

    public FlightSearchServiceClient(RestTemplate restTemplate, String flightSearchServiceUrl) {
        this.restTemplate = restTemplate;
        this.flightSearchServiceUrl = flightSearchServiceUrl;
    }

    public void sendFlightInformation(Flight flight) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Flight> request = new HttpEntity<>(flight, headers);

        restTemplate.postForObject(flightSearchServiceUrl + "/v1/api/flights", request, Flight.class);
    }
}

