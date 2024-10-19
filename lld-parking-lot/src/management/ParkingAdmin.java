package management;

import infrastructure.Entrance;
import infrastructure.Exit;
import infrastructure.ParkingLot;
import model.ParkingFloor;
import model.ParkingSpace;

public class ParkingAdmin {

    private ParkingLot parkingLot;

    private static volatile ParkingAdmin parkingAdminInstance;

    private ParkingAdmin(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public static ParkingAdmin getInstance(ParkingLot parkingLot) {
        if (parkingAdminInstance == null) {
            synchronized (ParkingAdmin.class) {
                if (parkingAdminInstance == null) {
                    parkingAdminInstance = new ParkingAdmin(parkingLot);
                }
            }
        }
        return parkingAdminInstance;
    }


    public boolean addParkingFloor(ParkingFloor parkingFloor) {
        if(parkingFloor == null) return false;
        parkingLot.getParkingFloors().add(parkingFloor);
        return true;
    }

    public boolean addParkingSpace(ParkingFloor parkingFloor, ParkingSpace parkingSpace) {
        if(parkingSpace == null || parkingFloor == null) return false;
        parkingLot.getParkingFloors().forEach( floor -> {
            floor.getParkingAvailabilityMap().put(parkingSpace, false);
        });
        return true;
    }

    public boolean addEntrance(Entrance entrance) {
        if (entrance == null) return false;
        parkingLot.getEntrances().add(entrance);
        return true;
    }

    public boolean addExit(Exit exit) {
        if (exit == null) return false;
        parkingLot.getExits().add(exit);
        return true;
    }

}
