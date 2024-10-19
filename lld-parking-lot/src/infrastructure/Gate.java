package infrastructure;

import management.ParkingAttendent;

public class Gate {
    private int id;
    protected ParkingAttendent parkingAttendent;
    public Gate(int id, ParkingAttendent parkingAttendent) {
        this.id = id;
        this.parkingAttendent = parkingAttendent;
    }
}
