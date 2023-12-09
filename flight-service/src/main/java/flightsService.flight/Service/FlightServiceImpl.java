package flightsService.flight.Service;
import java.util.List;
import java.util.stream.Collectors;
import flightsService.flight.Entity.Flight;
import flightsService.flight.Entity.FlightRequest;
import flightsService.flight.Entity.FlightResponse;
import flightsService.flight.Event.NewFlightEvent;
import flightsService.flight.Exception.FlightServiceCustomException;
import flightsService.flight.Repository.FlightRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    @Autowired
    private final FlightRepository flightRepository;


    private final KafkaTemplate<String, NewFlightEvent> kafkaTemplate;



    @Override
    public FlightResponse createFlight(FlightRequest flightRequest) {

        Flight flight = Flight.builder()
                .flightNumber(flightRequest.flightNumber())
                .origin(flightRequest.origin())
                .destination(flightRequest.destination())
                .departureDate(flightRequest.departureDate())
                .arrivalDate(flightRequest.arrivalDate())
                .totalSeats(flightRequest.totalSeats())
                .availableSeats(flightRequest.availableSeats())
                .amount(flightRequest.amount())
                .build();
        //log.info(flight);
        // Save the flight to FlightRepository
        flight = flightRepository.save(flight);

        FlightResponse flightResponse = new FlightResponse();
        BeanUtils.copyProperties(flight, flightResponse);


        kafkaTemplate.send("notificationTopic",new NewFlightEvent(flight.getFlightId(),flight.getFlightNumber(),flight.getOrigin(),flight.getDestination(),
                flight.getDepartureDate(),flight.getArrivalDate(),flight.getAmount(),flight.getTotalSeats(),flight.getAvailableSeats())
        );






        log.info("Flight Created {}", flightResponse.getFlightId());
        return flightResponse;

    }




    @Override
    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightResponse> flightResponseList = flights
                .stream()
                .map(this::mapToFlightResponse)
                .collect(Collectors.toList());

        return flightResponseList;
    }

    @Override
    public FlightResponse getFlightByNumber(String flightNumber) {
        log.info("Get the flight for flight Number: {}", flightNumber);
        Flight optionalFlight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(
                        () -> new FlightServiceCustomException("Flight with given number not found",
                                "FLIGHT_NOT_FOUND"));

        FlightResponse flightResponse = new FlightResponse();
        BeanUtils.copyProperties(optionalFlight, flightResponse);

        return flightResponse;

    }

    private FlightResponse mapToFlightResponse(Flight flight) {
        FlightResponse flightResponse = new FlightResponse();
        BeanUtils.copyProperties(flight, flightResponse);
        return flightResponse;

    }
}
