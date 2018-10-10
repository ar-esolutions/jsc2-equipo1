package com.esolutions.trainings.jsc2.model;

import java.util.Map;
import java.util.TreeMap;

public class Hotel {

        private Map<Long,Floor> floors= new TreeMap<>();

    public Hotel() {
    }

    public Hotel(Map<Long, Floor> floors) {
        this.floors = floors;
    }

    public Map<Long, Floor> getFloors() {
        return floors;
    }

    public void setFloors(Map<Long, Floor> floors) {
        this.floors = floors;
    }
}
