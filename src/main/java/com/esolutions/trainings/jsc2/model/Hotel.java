package com.esolutions.trainings.jsc2.model;

import java.util.ArrayList;

public class Hotel {

    private ArrayList<Floor> floors;

    public Hotel() {
    }

    public Hotel(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
}
