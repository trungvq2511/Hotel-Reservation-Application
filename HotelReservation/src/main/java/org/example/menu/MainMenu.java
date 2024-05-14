package org.example.menu;

import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.model.Reservation;
import org.example.service.CustomerService;
import org.example.service.ReservationService;
import org.example.service.ValidationService;

import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static ReservationService reservationService = ReservationService.getInstance();
    public static CustomerService customerService = CustomerService.getInstance();
    public static ValidationService validationService = ValidationService.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        int choice = 0;
        while (true) {
            System.out.println("Welcome to the Hotel Reservation Application");
            System.out.println("------------------------------------------");
            System.out.println("1.  Find and reserve a room");
            System.out.println("2.  See my reservations");
            System.out.println("3.  Create an account");
            System.out.println("4.  Admin");
            System.out.println("5.  Exit");
            System.out.println("------------------------------------------");
            System.out.println("Please select a number for the menu option");
            choice = validationService.validateInputMenu();
            switch (choice) {
                case 1: {
                    System.out.println("Enter CheckIn Date MM/dd/yyyy example 02/01/2020");
                    Date checkInDate = validationService.validateInputDate();
                    System.out.println("Enter checkOut Date MM/dd/yyyy example 02/01/2020");
                    boolean checkDate = false;
                    Date checkOutDate = null;
                    do {
                        checkOutDate = validationService.validateInputDate();
                        if (checkOutDate.after(checkInDate)) {
                            checkDate = true;
                        } else {
                            System.err.println("Check out date must after check in date");
                        }
                    } while (!checkDate);
                    Collection<IRoom> availableRoomList = reservationService.findRooms(checkInDate, checkOutDate);
                    availableRoomList.stream().forEach(availableRoom -> System.out.println(availableRoom));
                    if (availableRoomList.isEmpty()) {
                        Collection<IRoom> recommendedRoomList = reservationService.findRecommendedRooms(checkInDate, checkOutDate);
                        if (!recommendedRoomList.isEmpty()) {
                            System.out.println("We don't have any available room for your check-in check-out date");
                            System.out.println("There are some rooms available in next 7 days");
                            recommendedRoomList.stream().forEach(recommendedRoom -> System.out.println(recommendedRoom));
                        } else {
                            System.out.println("Sorry, we don't have any available room");
                        }
                    } else {
                        bookRoom(availableRoomList, checkInDate, checkOutDate);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Please enter your account email");
                    String email = validationService.validateInputEmail();
                    Customer customer = customerService.getCustomer(email);
                    if (customer != null) {
                        reservationService.getCustomersReservation(customer).stream().forEach(reservation -> System.out.println(reservation));
                    } else {
                        System.out.println("Customer email is not found");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter Email format: name@domain.com");
                    boolean checkEmail = false;
                    String email = null;
                    do {
                        email = validationService.validateInputEmail();
                        if (!validationService.isEmailExisted(email)) {
                            checkEmail = true;
                        }
                    } while (!checkEmail);
                    System.out.println("First Name");
                    String firstName = validationService.validateInputName();
                    System.out.println("Last Name");
                    String lastName = validationService.validateInputName();
                    customerService.addCustomer(firstName, lastName, email);

                    break;
                }
                case 4: {
                    AdminMenu.adminMenu();
                    break;
                }
                case 5: {
                    System.out.println("----------------EXISTING------------------");
                    System.exit(0);
                }
            }
        }
    }

    private static void bookRoom(Collection<IRoom> roomList, Date checkInDate, Date checkOutDate) {
        System.out.println("Would you like to book a room? y/n");
        String check = validationService.validateInputYesNo();
        if (check.equalsIgnoreCase("y")) {
            System.out.println("Do you have an account with us? y/n");
            String check2 = validationService.validateInputYesNo();
            if (check2.equalsIgnoreCase("y")) {
                System.out.println("Please enter your account email");
                String email = validationService.validateInputEmail();
                Customer customer = customerService.getCustomer(email);
                if (customer != null) {
                    System.out.println("What room number would you like to reserve?");
                    boolean checkSelectedRoom = false;
                    String roomNumber = null;
                    do {
                        roomNumber = scanner.nextLine();
                        if (roomList.contains(reservationService.getARoom(roomNumber))) {
                            checkSelectedRoom = true;
                        } else {
                            System.out.println("Please select a available room");
                        }
                    } while (!checkSelectedRoom);
                    IRoom aRoom = reservationService.getARoom(roomNumber);
                    Reservation reservation = reservationService.reserveARoom(customer, aRoom, checkInDate, checkOutDate);
                    System.out.println(reservation);
                } else {
                    System.out.println("Customer email is not found");
                }
            } else {
                System.out.println("Please create an account first");
            }
        }
    }

    public static Date add7DaysToDate(Date date) {
        return new Date(date.getTime() + (1000 * 60 * 60 * 24 * 7));
    }
}