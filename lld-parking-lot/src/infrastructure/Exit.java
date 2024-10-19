package infrastructure;

import enums.ParkingSpaceType;
import enums.PaymentStatus;
import exception.InvalidParkingException;
import management.ParkingAttendent;
import model.ParkingPaymentResponse;
import model.ParkingTicket;

import java.time.Duration;

public class Exit extends Gate{

    public Exit(int id, ParkingAttendent parkingAttendent) {
        super(id, parkingAttendent);
    }

    public ParkingPaymentResponse processVehicleExit(ParkingTicket parkingTicket) {
        Duration duration = Duration.between(parkingTicket.getEntryTime(), parkingTicket.getExitTime());
        double totalAmount = 0.0;
        try {
            totalAmount = parkingAttendent.paymentForParking(parkingTicket, duration);
        } catch (InvalidParkingException ex) {
            System.out.println(ex.getMessage());
        }
        return new ParkingPaymentResponse(PaymentStatus.COMPLETED, totalAmount);
    }

}
