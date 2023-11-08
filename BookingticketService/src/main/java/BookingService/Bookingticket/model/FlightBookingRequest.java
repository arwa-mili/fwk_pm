package BookingService.Bookingticket.model;

import lombok.Data;

@Data
public final class FlightBookingRequest extends BookingRequest {
    private String flightNumber;

}