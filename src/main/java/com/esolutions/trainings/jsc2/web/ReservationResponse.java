package com.esolutions.trainings.jsc2.web;

import java.math.BigDecimal;

public class ReservationResponse {

    private boolean booked ;
    private BigDecimal price;

    public ReservationResponse() {
    }

    public ReservationResponse(boolean booked, BigDecimal price) {
        this.booked = booked;
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
