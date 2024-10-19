package infrastructure;

import enums.VehicleType;
import exception.ParkingSpaceNotFound;
import management.ParkingAttendent;
import model.ParkingTicket;

public class Entrance extends Gate{

    public Entrance(int id, ParkingAttendent parkingAttendent) {
        super(id, parkingAttendent);
    }

    public ParkingTicket generateParkingTicket(VehicleType vehicleType, int vehicleNumber) {
        try {
           return parkingAttendent.processVehicleEntry(vehicleType,vehicleNumber);
        } catch (ParkingSpaceNotFound ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
