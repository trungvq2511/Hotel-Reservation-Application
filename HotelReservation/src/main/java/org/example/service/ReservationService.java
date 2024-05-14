package org.example.service;

import org.example.HotelApplication;
import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.model.Reservation;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.example.HotelApplication.reservationList;
import static org.example.HotelApplication.roomList;

public class ReservationService {
    private static ReservationService singleInstance = null;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (singleInstance == null) {
            singleInstance = new ReservationService();
        }
        return singleInstance;
    }

    public void addRoom(IRoom room) {
        HotelApplication.roomList.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : roomList) {
            if (room.getRoomNumber().equalsIgnoreCase(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        room.setFree(false);
        reservationList.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRoomList = new ArrayList<>();
        for (IRoom room : roomList) {
            if (room.isFree()) {
                availableRoomList.add(room);
            }
        }
        for (Reservation reservation : reservationList) {
            if ((checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate()))
                    || (checkInDate.after(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())))
                availableRoomList.add(reservation.getRoom());
        }
        return availableRoomList;
    }

    public Collection<IRoom> findRecommendedRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> recommendedRoomList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            LocalDate checkInLocalDatePlus7Days = checkInDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate().plusDays(7);
            LocalDate checkOutLocalDatePlus7Days = checkInDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate().plusDays(7);
            LocalDate checkInLocalDate = reservation.getCheckInDate().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
            LocalDate checkOutLocalDate = reservation.getCheckOutDate().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
            if (checkInLocalDatePlus7Days.isAfter(checkInLocalDate) && checkOutLocalDatePlus7Days.isAfter(checkOutLocalDate))
                recommendedRoomList.add(reservation.getRoom());
        }
        return recommendedRoomList;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservationList = new ArrayList<>();
        reservationList.stream().forEach(reservation -> {
            if (reservation.getCustomer().equals(customer)) {
                customerReservationList.add(reservation);
            }
        });
        return customerReservationList;
    }

    public void printAllReservation() {
        reservationList.stream().forEach(reservation -> {
            System.out.println(reservation.toString());
        });
    }

}
