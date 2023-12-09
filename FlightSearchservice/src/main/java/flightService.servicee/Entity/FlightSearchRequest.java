package flightService.servicee.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



public record FlightSearchRequest(

        String origin,
        String destination,
        LocalDate travelDate,
        int passengers) {



}
