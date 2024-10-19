package management;

import enums.ParkingSpaceType;
import enums.PaymentMode;
import enums.VehicleType;
import exception.InvalidParkingException;
import exception.ParkingSpaceNotFound;
import infrastructure.ParkingLot;
import model.ParkingAllocationResponse;
import model.ParkingTicket;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingAttendent {
    private int parkingAttendentId;
    private ParkingLot parkingLot;


    public ParkingAttendent(int parkingAttendentId, ParkingLot parkingLot) {
        this.parkingAttendentId = parkingAttendentId;
        this.parkingLot = parkingLot;
    }

    public ParkingTicket processVehicleEntry(VehicleType vehicleType, int vehicleNumber) throws ParkingSpaceNotFound {
        ParkingAllocationResponse parkingAllocationResponse = parkingLot.allocateParkingSpace(vehicleType);
        if (!parkingAllocationResponse.getIsAllocated()) {
            throw new ParkingSpaceNotFound("Parking Space Not Available for Vehicle " + vehicleType.toString() + " " + vehicleNumber);
        }
        ParkingTicket parkingTicket = new ParkingTicket(
                LocalDateTime.now(),
                vehicleNumber,
                PaymentMode.CASH,
                getParkingSpaceType(vehicleType),
                parkingAllocationResponse.getSpotId(),
                parkingAllocationResponse.getLevelId()
        );
        System.out.println("Parking Allocation : " + parkingAllocationResponse.getIsAllocated() +", Parking Ticket : " + parkingTicket);
        return parkingTicket;
    }

    public Double paymentForParking(ParkingTicket parkingTicket, Duration duration) throws InvalidParkingException {
        double totalCost = calculateCost(parkingTicket.getParkingSpaceType(), duration);
        if (totalCost == 0.0) {
            throw new InvalidParkingException("Invalid Parking Space");
        }
        parkingLot.releaseParkingSpace(parkingTicket.getSpotId());
        System.out.println("Cost Calculation Done. Total Cost: " + totalCost+" for Parking Ticket: " + parkingTicket+ "and release spot Id: "+parkingTicket.getSpotId());
        return totalCost;
    }

    private double calculateCost(ParkingSpaceType parkingSpaceType, Duration duration) {
        double cost = 0;
        switch (parkingSpaceType) {
            case CAR_PARKING:
                cost = duration.toHours() * 50;
                break;
            case BIKE_PARKING:
                cost = duration.toHours() * 30;
                break;
            case TRUCK_PARKING:
                cost = duration.toHours() * 100;
                break;
            default:
                cost = 0;
        }
        return cost;
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

}
