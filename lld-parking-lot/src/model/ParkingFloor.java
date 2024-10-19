package model;

import enums.ParkingSpaceType;
import infrastructure.ParkingDisplayBoard;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloor {
    private int levelId;
    private HashMap<ParkingSpace, Boolean> parkingAvailabilityMap;
    private ParkingDisplayBoard parkingDisplayBoard;


    public void setParkingDisplayBoard(ParkingDisplayBoard parkingDisplayBoard) {
        this.parkingDisplayBoard = parkingDisplayBoard;
    }

    public ParkingFloor(int levelId, int spaceCount) {
        this.levelId = levelId;
        this.parkingAvailabilityMap = new HashMap<>();
        int bikeSpots = (int) (spaceCount * 0.5);
        int carSpots = (int) (spaceCount * 0.4);
        Map<ParkingSpaceType, Integer> countMap = new HashMap<>();

        for (int i = 1; i <= bikeSpots; i++) {
            parkingAvailabilityMap.put(new ParkingSpace(i, ParkingSpaceType.BIKE_PARKING), false);
            countMap.put(
                    ParkingSpaceType.BIKE_PARKING,
                    countMap.getOrDefault(
                        ParkingSpaceType.BIKE_PARKING,
                        0) + 1
            );
        }
        for (int i = bikeSpots+1; i <= spaceCount; i++) {
            parkingAvailabilityMap.put(new ParkingSpace(i, ParkingSpaceType.CAR_PARKING), false);
            countMap.put(
                    ParkingSpaceType.CAR_PARKING,
                    countMap.getOrDefault(
                            ParkingSpaceType.CAR_PARKING,
                            0) + 1
            );
        }
        for (int i = bikeSpots+carSpots+1; i <= spaceCount; i++) {
            parkingAvailabilityMap.put(new ParkingSpace(i, ParkingSpaceType.TRUCK_PARKING), false);
            countMap.put(
                    ParkingSpaceType.TRUCK_PARKING,
                    countMap.getOrDefault(
                            ParkingSpaceType.TRUCK_PARKING,
                            0) + 1
            );
        }
        this.parkingDisplayBoard = new ParkingDisplayBoard(countMap);

    }
    public int getLevelId() {
        return levelId;
    }


    public HashMap<ParkingSpace, Boolean> getParkingAvailabilityMap() {
        return parkingAvailabilityMap;
    }

    public ParkingDisplayBoard getParkingDisplayBoard() {
        return parkingDisplayBoard;
    }


}
