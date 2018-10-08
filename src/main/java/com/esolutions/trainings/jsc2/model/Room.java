package com.esolutions.trainings.jsc2.model;

public class Room {

    private Guest guest ;

    public Room(Guest guest) {
        this.guest = guest;
    }

    public Room() {
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
