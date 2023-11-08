package BookingService.Bookingticket.service;

import BookingService.Bookingticket.model.BookingRequest;
import BookingService.Bookingticket.model.BookingResponse;

public interface BookingService {

    public BookingResponse createBooking(BookingRequest bookingRequest);

}