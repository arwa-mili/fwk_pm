package BookingService.Bookingticket.service;


import BookingService.Bookingticket.entity.Flight;
import BookingService.Bookingticket.entity.FlightBooking;
import BookingService.Bookingticket.enums.BookingStatus;
import BookingService.Bookingticket.event.FlightBookingCompleted;
import BookingService.Bookingticket.model.BookingRequest;
import BookingService.Bookingticket.model.BookingResponse;
import BookingService.Bookingticket.model.FlightBookingRequest;
import BookingService.Bookingticket.model.FlightBookingResponse;
import BookingService.Bookingticket.repository.FlightBookingRepository;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@Qualifier("flightBookingService")
public class FlightBookingService implements BookingService {

    private final FlightBookingRepository flightBookingRepository;
    private final WebClient webClient;
    private final ObservationRegistry observationRegistry;

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


            Flight flightNumberN = webClient
                .get()
                .uri("http://localhost:5217/v1/api/search/{flightNumber}",
                        flightBookingRequest.getFlightNumber())
                .retrieve()
                .bodyToMono(Flight.class)
                .block();
            log.info("{}",flightNumberN);

            FlightBooking flightBooking = new FlightBooking();
      /*  try {  */
            flightBooking.setBookingNumber(UUID.randomUUID().toString());
            flightBooking.setFlightNumber(flightNumberN.getFlightNumber());
            flightBooking.setDestination(flightNumberN.getDestination());

            flightBooking.setBookingDate(LocalDate.now());
            flightBooking.setStatus(BookingStatus.CREATED.name());
            flightBooking.setPassengerName(flightBookingRequest.getPassengerName());
            flightBooking.setAmount(flightBookingRequest.getAmount());
            flightBooking.setPaymentMode(flightBookingRequest.getPaymentMode().name());


            FlightBookingCompleted flightBookingCompleted = new FlightBookingCompleted(
                    flightBooking.getBookingNumber());


        String updateSeatsUrl = "http://localhost:5217/v1/api/search/updateSeats/" +flightNumberN.getFlightNumber();
        webClient.put()
                .uri(updateSeatsUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        log.info("Sending event to notificationTopic with event {}",flightBookingCompleted);


            kafkaTemplate.send("bookingnotificationTopic", flightBookingCompleted);


/*
        } catch (Exception e) {

            log.info("{}",flightNumberN);

        }

        */

        return flightBooking;

};}
