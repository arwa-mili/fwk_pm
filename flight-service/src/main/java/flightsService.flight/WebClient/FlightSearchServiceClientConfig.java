package flightsService.flight.WebClient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FlightSearchServiceClientConfig {

    @Value("${localhost:5967}")
    private String flightSearchServiceUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FlightSearchServiceClient flightSearchServiceClient(RestTemplate restTemplate) {
        return new FlightSearchServiceClient(restTemplate, flightSearchServiceUrl);
    }
}

