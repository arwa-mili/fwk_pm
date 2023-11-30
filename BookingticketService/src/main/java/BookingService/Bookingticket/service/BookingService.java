package BookingService.Bookingticket.service;

import BookingService.Bookingticket.model.BookingRequest;
import BookingService.Bookingticket.model.BookingResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    BookingResponse createBooking(BookingRequest bookingRequest);

}