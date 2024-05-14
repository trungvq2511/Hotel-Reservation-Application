package org.example;

import org.example.enumeration.RoomType;
import org.example.menu.MainMenu;
import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.model.Reservation;
import org.example.model.Room;
import org.example.service.CustomerService;
import org.example.service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HotelApplication {
    public static List<IRoom> roomList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();
    public static List<Reservation> reservationList = new ArrayList<>();
    public static ReservationService reservationService = ReservationService.getInstance();
    public static CustomerService customerService = CustomerService.getInstance();

    public static void main(String[] args) throws ParseException {
        initData();
        MainMenu.mainMenu();
    }

    private static void initData() throws ParseException {
        Room room1 = new Room("1", 100D, RoomType.SINGLE);
        Room room2 = new Room("2", 200D, RoomType.DOUBLE);
        Room room3 = new Room("3", 300D, RoomType.DOUBLE);
        reservationService.addRoom(room1);
        reservationService.addRoom(room2);
//        reservationService.addRoom(room3);

        customerService.addCustomer("Trung", "Vu", "trung@email.com");

        Customer customer = customerService.getCustomer("trung@email.com");
        reservationService.reserveARoom(customer, room1,
                new SimpleDateFormat("MM/dd/yyyy").parse("01/08/2023"),
                new SimpleDateFormat("MM/dd/yyyy").parse("01/30/2023"));
//        reservationService.reserveARoom(customer, room1,
//                new SimpleDateFormat("MM/dd/yyyy").parse("01/08/2023"),
//                new SimpleDateFormat("MM/dd/yyyy").parse("01/09/2023"));
    }
}
