package model;

import enums.ParkingSpaceType;

public class ParkingSpace {
    private int spotId;
    private ParkingSpaceType parkingSpaceType;

    public ParkingSpace(int spotId, ParkingSpaceType parkingSpaceType) {
        this.spotId = spotId;
        this.parkingSpaceType = parkingSpaceType;
    }
    public int getSpotId() {
        return spotId;
    }

    public ParkingSpaceType getParkingSpaceType() {
        return parkingSpaceType;
    }

}
