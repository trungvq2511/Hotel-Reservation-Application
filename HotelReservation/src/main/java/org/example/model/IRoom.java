package org.example.model;

import org.example.enumeration.RoomType;

public interface IRoom {
    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();

    public void setFree(boolean free);
}
