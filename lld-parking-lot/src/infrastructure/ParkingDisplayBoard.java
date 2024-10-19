package infrastructure;

import enums.ParkingSpaceType;

import java.util.Map;

public class ParkingDisplayBoard {
    private Map<ParkingSpaceType, Integer> vacantSpots;
    public ParkingDisplayBoard(Map<ParkingSpaceType, Integer> vacantSpots) {
        this.vacantSpots = vacantSpots;
    }
}
