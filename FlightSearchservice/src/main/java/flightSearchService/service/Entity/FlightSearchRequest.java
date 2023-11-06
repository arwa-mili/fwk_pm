package flightSearchService.service.Entity;

import java.time.LocalDate;

public record FlightSearchRequest(
        String origin,
        String destination,
        LocalDate travelDate,
        int passengers) {
}
