package BookingService.Bookingticket.repository;


import BookingService.Bookingticket.entity.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

}