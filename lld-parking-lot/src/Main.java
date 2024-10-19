import enums.VehicleType;
import infrastructure.Entrance;
import infrastructure.Exit;
import infrastructure.Gate;
import infrastructure.ParkingLot;
import management.ParkingAdmin;
import management.ParkingAttendent;
import model.ParkingFloor;
import model.ParkingPaymentResponse;
import model.ParkingTicket;

import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Creating a instance of Parking Lot
        ParkingLot parkingLot = ParkingLot.getInstance("GoJek Parking");
        // Creating Parking Admin
        ParkingAdmin parkingAdmin = ParkingAdmin.getInstance(parkingLot);

        // Adding Parking Floors and Spaces
        ParkingFloor floor1 = new ParkingFloor(1, 10);
        ParkingFloor floor2 = new ParkingFloor(2, 10);
        parkingAdmin.addParkingFloor(floor1);
        parkingAdmin.addParkingFloor(floor2);

        ParkingAttendent entranceAttendent  = new ParkingAttendent(1, parkingLot);
        ParkingAttendent exitAttendent  = new ParkingAttendent(2, parkingLot);


        // Creating a Entrance and Exit Gate
        Entrance entrance = new Entrance(101, entranceAttendent);
        Exit exit = new Exit(102, exitAttendent);

        parkingAdmin.addEntrance(entrance);
        parkingAdmin.addExit(exit);

        ParkingTicket parkingTicket = entrance.generateParkingTicket(VehicleType.CAR, 1234);

        // adding 2 hours to see cost
        parkingTicket.setExitTime(LocalDateTime.now().plusHours(2));

        ParkingPaymentResponse parkingPaymentResponse = exit.processVehicleExit(parkingTicket);

        System.out.println("Parking Payment Status : "+parkingPaymentResponse.getPaymentStatus()+ " and total Amount : "+parkingPaymentResponse.getTotalAmount());

    }
}