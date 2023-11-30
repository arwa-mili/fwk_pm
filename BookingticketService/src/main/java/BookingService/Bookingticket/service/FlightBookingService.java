package BookingService.Bookingticket.service;


import BookingService.Bookingticket.entity.FlightBooking;
import BookingService.Bookingticket.enums.BookingStatus;
import BookingService.Bookingticket.event.BookingCompletedEvent;
import BookingService.Bookingticket.event.FlightBookingCompleted;
import BookingService.Bookingticket.model.BookingRequest;
import BookingService.Bookingticket.model.BookingResponse;
import BookingService.Bookingticket.model.FlightBookingRequest;
import BookingService.Bookingticket.model.FlightBookingResponse;
import BookingService.Bookingticket.repository.FlightBookingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Qualifier("flightBookingService")
public class FlightBookingService implements BookingService {

    private final FlightBookingRepository flightBookingRepository;

    @Autowired
    private KafkaTemplate<String, FlightBookingCompleted> kafkaTemplate;

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
        try {
            flightBooking.setBookingNumber(UUID.randomUUID().toString());
            flightBooking.setFlightNumber(flightBookingRequest.getFlightNumber());

            flightBooking.setBookingDate(LocalDate.now());
            flightBooking.setPassengerName(flightBookingRequest.getPassengerName());

            flightBooking.setAmount(flightBookingRequest.getAmount());
            flightBooking.setPaymentMode(flightBookingRequest.getPaymentMode().name());
            flightBooking.setStatus(BookingStatus.CREATED.name());
            FlightBookingCompleted flightBookingCompleted = new FlightBookingCompleted(
                    flightBooking.getBookingNumber());
            //log.info("Sending event to notificationTopic with event {}",flightBookingCompleted);


            kafkaTemplate.send("notificationTopic", flightBookingCompleted);



        } catch (Exception e) {
            flightBooking.setStatus(BookingStatus.CANCELLED.name());

        }
        return flightBooking;

};}
