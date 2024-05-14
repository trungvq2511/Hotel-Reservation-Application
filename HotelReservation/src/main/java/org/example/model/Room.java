package org.example.model;

import org.example.enumeration.RoomType;

public class Room implements IRoom, Comparable<IRoom> {
    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;
    protected boolean free = true;

    public Room() {
    }

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomType getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return free;
    }

    @Override
    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber
                + (enumeration.equals(RoomType.SINGLE) ? " Single bed room" : " Double bed room")
                + " Room price: " + price + "$";
    }

    @Override
    public int compareTo(IRoom o) {
        return roomNumber.compareTo(o.getRoomNumber());
    }
}
