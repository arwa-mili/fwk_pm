package BookingService.Bookingticket.service;


import BookingService.Bookingticket.entity.FlightBooking;
import BookingService.Bookingticket.enums.BookingStatus;
import BookingService.Bookingticket.model.BookingRequest;
import BookingService.Bookingticket.model.BookingResponse;
import BookingService.Bookingticket.model.FlightBookingRequest;
import BookingService.Bookingticket.model.FlightBookingResponse;
import BookingService.Bookingticket.repository.FlightBookingRepository;
import jakarta.inject.Qualifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class FlightBookingService implements BookingService {

    private final FlightBookingRepository flightBookingRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        if (!(bookingRequest instanceof FlightBookingRequest)) {
            throw new IllegalArgumentException("Invalid booking type");
        }

        FlightBooking flightBooking = mapToFlightBooking(bookingRequest);

        // validate flight Booking

        flightBooking = flightBookingRepository.save(flightBooking);

        FlightBookingResponse flightBookingResponse = new FlightBookingResponse();
        BeanUtils.copyProperties(flightBooking, flightBookingResponse);

        return flightBookingResponse;
    }

    private FlightBooking mapToFlightBooking(BookingRequest bookingRequest) {
        FlightBookingRequest flightBookingRequest = (FlightBookingRequest) bookingRequest;

        FlightBooking flightBooking = new FlightBooking();

        flightBooking.setBookingNumber(UUID.randomUUID().toString());
        flightBooking.setFlightNumber(flightBookingRequest.getFlightNumber());

        flightBooking.setBookingDate(LocalDate.now());
        flightBooking.setPassengerName(flightBookingRequest.getPassengerName());

        flightBooking.setAmount(flightBookingRequest.getAmount());
        flightBooking.setPaymentMode(flightBookingRequest.getPaymentMode().name());
        flightBooking.setStatus(BookingStatus.CREATED.name());

        return flightBooking;

    }
}
