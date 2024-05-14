package org.example.menu;

import org.example.HotelApplication;
import org.example.enumeration.RoomType;
import org.example.model.Room;
import org.example.service.CustomerService;
import org.example.service.ReservationService;
import org.example.service.ValidationService;

import java.util.Scanner;

public class AdminMenu {
    public static ReservationService reservationService = ReservationService.getInstance();
    public static CustomerService customerService = CustomerService.getInstance();
    public static ValidationService validationService = ValidationService.getInstance();

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        LOOP:
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("------------------------------------------");
            System.out.println("1.  See all Customers");
            System.out.println("2.  See all Rooms");
            System.out.println("3.  See all Reservations");
            System.out.println("4.  Add a Room");
            System.out.println("5.  Back to Main Menu");
            System.out.println("------------------------------------------");
            System.out.println("Please select a number for the menu option");
            int choice = validationService.validateInputMenu();
            switch (choice) {
                case 1: {
                    System.out.println("--------------CUSTOMER LIST--------------");
                    customerService.getAllCustomers().stream().forEach(customer -> System.out.println(customer.toString()));
                    break;
                }
                case 2: {
                    System.out.println("--------------ROOM LIST--------------");
                    HotelApplication.roomList.stream().forEach(iRoom -> System.out.println(iRoom.toString()));
                    break;
                }
                case 3: {
                    System.out.println("--------------RESERVATION LIST--------------");
                    HotelApplication.reservationList.stream().forEach(reservation -> System.out.println(reservation.toString()));
                    break;
                }
                case 4: {
                    String check = "n";
                    do {
                        System.out.println("Enter room number");
                        String roomNumber = validationService.validateInputRoomNumber();
                        System.out.println("Enter price per night");
                        double price = validationService.validateInputPrice();
                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        RoomType roomType = validationService.validateInputRoomType();
                        reservationService.addRoom(new Room(roomNumber, price, roomType));

                        System.out.println("Would you like to add another room y/n");
                        check = validationService.validateInputYesNo();
                    } while (check.equalsIgnoreCase("y"));
                    break;
                }
                case 5: {
                    break LOOP;
                }
            }
        }
    }
}