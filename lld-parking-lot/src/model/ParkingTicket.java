package model;

import enums.ParkingSpaceType;
import enums.PaymentMode;

import java.time.LocalDateTime;
import java.util.Random;

public class ParkingTicket {

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private int vehicleNumber;
    private PaymentMode paymentMode;
    private ParkingSpaceType parkingSpaceType;
    private int spotId;
    private int ticketId;
    private int levelId;


    public ParkingTicket(LocalDateTime entryTime, int vehicleNumber, PaymentMode paymentMode, ParkingSpaceType parkingSpaceType, int spotId, int levelId) {
        this.entryTime = entryTime;
        this.vehicleNumber = vehicleNumber;
        this.paymentMode = paymentMode;
        this.parkingSpaceType = parkingSpaceType;
        this.spotId = spotId;
        this.ticketId = new Random().nextInt(10000);
        this.levelId = levelId;
    }


    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public ParkingSpaceType getParkingSpaceType() {
        return parkingSpaceType;
    }


    public int getSpotId() {
        return spotId;
    }

    public int getLevelId() {
        return levelId;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", vehicleNumber=" + vehicleNumber +
                ", paymentMode=" + paymentMode +
                ", parkingSpaceType=" + parkingSpaceType +
                ", spotId=" + spotId +
                ", ticketId=" + ticketId +
                ", levelId=" + levelId +
                '}';
    }
}
