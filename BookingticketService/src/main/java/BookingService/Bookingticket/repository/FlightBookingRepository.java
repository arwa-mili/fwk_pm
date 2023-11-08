package BookingService.Bookingticket.repository;


import BookingService.Bookingticket.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

}