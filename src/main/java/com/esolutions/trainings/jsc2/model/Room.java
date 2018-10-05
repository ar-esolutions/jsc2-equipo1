package com.esolutions.trainings.jsc2.model;

public class Room {

    private Guest guest ;
    private long roomNumber;

    public Room(Guest guest, long roomNumber) {
        this.guest = guest;
        this.roomNumber = roomNumber;
    }

    public Room() {
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
