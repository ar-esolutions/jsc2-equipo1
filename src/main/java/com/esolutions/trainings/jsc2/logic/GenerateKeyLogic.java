package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.web.WifiSsidResponse;

public class GenerateKeyLogic {

    private String parseBinaryFloorAndRoom(int floor, int room) {
        String binaryFloor = Integer.toBinaryString(floor);  // Paso el int floor a binario y lo guardo en string
        String binaryRoom = Integer.toBinaryString(room);    // Paso el int room a binario y lo guardo en string

        return binaryFloor.concat(binaryRoom);
    }

    private int countZeros(String chain) {
        int suma = 0;
        int maximo = 0;

        final Estados eFinal = Estados.Q1;
        Estados actual = Estados.Q1;

        for (int i = 0; i < chain.length(); i++) {
            if (actual == Estados.Q1) {
                if (suma > maximo) {
                    maximo = suma;
                }
                suma = 0;
            }
            if (chain.charAt(i) == '0') {
                suma++;
                if (Estados.Q1.equals(actual)) {
                    actual = Estados.Q2;
                }
            } else {
                if (Estados.Q2.equals(actual)) {
                    actual = Estados.Q1;
                }
            }
            if ((i == chain.length() - 1) && actual == eFinal && suma > maximo) {
                maximo = suma;
            }
        }
        return maximo;
    }

    private String concatenateSsid(int floor, int room, int ceros) {
        if (ceros == 0) {
            return "HAND-" + floor + "-" + room;
        } else {
            return "HAND-" + floor + "-" + room + "-" + ceros;
        }
    }

    public WifiSsidResponse generateSsid(int floor, int room) {
        String cadena = parseBinaryFloorAndRoom(floor, room);
        int zero = countZeros(cadena);
        String ssid = concatenateSsid(floor, room, zero);
        return new WifiSsidResponse(ssid);
    }
}
