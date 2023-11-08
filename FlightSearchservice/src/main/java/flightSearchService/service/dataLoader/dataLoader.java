package flightSearchService.service.dataLoader;

import flightSearchService.service.Entity.Flight;
import flightSearchService.service.Repository.FlightSearchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class dataLoader implements CommandLineRunner {
    private FlightSearchRepository flightSearchRepository;

    public dataLoader(FlightSearchRepository flightSearchRepository) {
        this.flightSearchRepository = flightSearchRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Remove existing flights (optional)
        flightSearchRepository.deleteAll();

        // Create sample flights
        Flight flight1 = new Flight(1l, "EK203", "DXB", "SWZ",
                LocalDate.parse("2023-06-23"), LocalDate.parse("2023-06-23"), 15000.00, 250, 130);
        Flight flight2 = new Flight(2l, "EK204", "DXB", "SWZ",
                LocalDate.parse("2023-06-22"), LocalDate.parse("2023-06-24"), 18000.00, 250, 200);
        Flight flight3 = new Flight(3l, "EK205", "IND", "DXB",
                LocalDate.parse("2023-06-25"), LocalDate.parse("2023-06-27"), 10000.00, 250, 150);

        // Save flights to the database
        flightSearchRepository.saveAll(Arrays.asList(flight1, flight2, flight3));

    }
}