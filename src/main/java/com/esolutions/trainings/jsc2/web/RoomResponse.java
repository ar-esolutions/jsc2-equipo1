package com.esolutions.trainings.jsc2.web;

public class RoomResponse {
    private Integer guest;

    public RoomResponse(Integer guest) {
        this.guest = guest;
    }

    public Integer getGuest() {
        return guest;
    }

    public void setGuest(Integer guest) {
        this.guest = guest;
    }
}
