package com.esolutions.trainings.jsc2.logic;

public class Princ {
    public static void main(String[] args) {
        WifiPasswordLogic wp = new WifiPasswordLogic();
        String pass = wp.calculatePassword(5,5);
        System.out.println(pass);
    }
}
