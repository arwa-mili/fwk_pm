package notificationService.NotificationService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightBookingCompleted {
    private String BookingNumber;

}
