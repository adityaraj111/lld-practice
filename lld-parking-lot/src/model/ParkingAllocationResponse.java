package model;

public class ParkingAllocationResponse {
    private Boolean isAllocated;
    private int levelId;
    int spotId;
    public ParkingAllocationResponse(Boolean isAllocated) {
        this.isAllocated = isAllocated;
    }
    public ParkingAllocationResponse(Boolean isAllocated, int  levelId, int spotId) {
        this.levelId = levelId;
        this.spotId = spotId;
        this.isAllocated = isAllocated;
    }

    public int getLevelId() {
        return levelId;
    }

    public Boolean getIsAllocated() {
        return isAllocated;
    }

    public int getSpotId() {
        return spotId;
    }
}
