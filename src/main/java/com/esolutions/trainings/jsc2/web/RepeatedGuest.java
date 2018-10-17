package com.esolutions.trainings.jsc2.web;

import java.util.LinkedList;
import java.util.List;

public class RepeatedGuest {

    private List lastNames= new LinkedList();

    public RepeatedGuest() {
    }

    public RepeatedGuest(List lastNames) {
        this.lastNames = lastNames;
    }

    public List getLastNames() {
        return lastNames;
    }

    public void setLastNames(List lastNames) {
        this.lastNames = lastNames;
    }
}
