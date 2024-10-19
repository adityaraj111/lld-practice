package infrastructure;

import enums.ParkingSpaceType;
import enums.VehicleType;
import model.ParkingAllocationResponse;
import model.ParkingFloor;
import model.ParkingSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private static volatile ParkingLot parkingLot;

    private String parkingLotName;
    private List<Entrance> entrances;
    private List<Exit> exits;
    private List<ParkingFloor> parkingFloors;

    private ParkingLot(String parkingLotName) {
        this.parkingLotName = parkingLotName;
        this.parkingFloors = new ArrayList<>();
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    public static ParkingLot getInstance(String parkingLotName) {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot(parkingLotName);
                }
            }
        }
        return parkingLot;
    }

    private Boolean checkAvailability(VehicleType vehicleType) {
        Boolean isAvailable = false;
        ParkingSpaceType parkingSpaceType = getParkingSpaceType(vehicleType);
        for (ParkingFloor parkingFloor : parkingFloors) {
            for(Map.Entry<ParkingSpace, Boolean>  entry: parkingFloor.getParkingAvailabilityMap().entrySet()) {
                if(entry.getKey().getParkingSpaceType() == parkingSpaceType
                        && !entry.getValue()) {
                    return true;
                }
            }
        }
       return isAvailable;
    }

    public synchronized ParkingAllocationResponse allocateParkingSpace(VehicleType vehicleType) {
        if (!checkAvailability(vehicleType)) {
            return new ParkingAllocationResponse(false);
        }
        // book a spot
        ParkingSpaceType parkingSpaceType = getParkingSpaceType(vehicleType);
        for (ParkingFloor parkingFloor : parkingFloors) {
            for(Map.Entry<ParkingSpace, Boolean> entry : parkingFloor.getParkingAvailabilityMap().entrySet()) {
                if (entry.getKey().getParkingSpaceType() == parkingSpaceType && !entry.getValue()) {
                    entry.setValue(true);
                    return new ParkingAllocationResponse(true, parkingFloor.getLevelId(), entry.getKey().getSpotId());
                }
            }
        }
        return new ParkingAllocationResponse(false);

    }

    public void releaseParkingSpace(int spotId) {
        parkingFloors.forEach(parkingFloor -> {
            parkingFloor.getParkingAvailabilityMap().entrySet().stream()
                    .filter(entry -> entry.getKey().getSpotId() == spotId)
                    .findFirst()
                    .ifPresent(entry -> entry.setValue(false));
        });
    }

    private ParkingSpaceType getParkingSpaceType(VehicleType vehicleType) {
        ParkingSpaceType parkingSpaceType;
        switch (vehicleType) {
            case CAR:
                parkingSpaceType =  ParkingSpaceType.CAR_PARKING;
                break;
            case BIKE:
                parkingSpaceType = ParkingSpaceType.BIKE_PARKING;
                break;
            case TRUCK:
                parkingSpaceType = ParkingSpaceType.TRUCK_PARKING;
                break;
            default:
                parkingSpaceType = null;
        }
        return parkingSpaceType;
    }

    public List<Entrance> getEntrances() {
        return entrances;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }
}
