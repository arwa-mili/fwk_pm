package BookingService.Bookingticket.model;


import lombok.Data;

@Data
public final class FlightBookingResponse extends BookingResponse {
    private String flightNumber;
}