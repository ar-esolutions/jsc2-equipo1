package com.esolutions.trainings.jsc2.model;
import java.util.Map;

public class Floor {

    private Map<Long,Room> rooms;

    public Floor(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }

    public Floor() {
    }

    public Map<Long, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }
}
