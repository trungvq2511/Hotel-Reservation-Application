package org.example.model;

public class FreeRoom extends Room {

    public FreeRoom() {
        this.setPrice(0D);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumeration=" + enumeration +
                '}';
    }
}
