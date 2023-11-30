package flightService.servicee.Entity;

import lombok.Data;

import java.time.LocalDate;



public record FlightSearchRequest(

        String origin,
        String destination,
        LocalDate travelDate,
        int passengers) {
        public String getOrigin() {

        return(this.origin);
    }


    public String getDestination() {

        return(this.destination);
    }


    public LocalDate getTravelDate() {

        return(this.travelDate);
    }
}
