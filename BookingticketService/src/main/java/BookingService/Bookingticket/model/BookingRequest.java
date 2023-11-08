package BookingService.Bookingticket.model;

import BookingService.Bookingticket.enums.PaymentMode;
import lombok.Data;

@Data
public sealed class BookingRequest permits FlightBookingRequest, HotelBookingRequest {

    String passengerName;
    double amount;
    PaymentMode paymentMode;
}
