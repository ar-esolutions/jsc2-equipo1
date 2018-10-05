package com.esolutions.trainings.jsc2.model;

import java.util.ArrayList;

public class Floor {

    private long floorNumber;
    private ArrayList<Room> rooms;

    public Floor(long floorNumber, ArrayList<Room> rooms) {
        this.floorNumber = floorNumber;
        this.rooms = rooms;
    }

    public Floor() {
    }

    public long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
