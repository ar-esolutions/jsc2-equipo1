package com.esolutions.trainings.jsc2.web;

public class ReservationResponse {

    private boolean booked ;
    private double price;

    public ReservationResponse() {
    }

    public ReservationResponse(boolean booked, double price) {
        this.booked = booked;
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}