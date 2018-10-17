package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.web.WifiPasswordResponse;

public class WifiPasswordLogic {

    public WifiPasswordResponse calculatePassword(int floor, int room) {
        String cadena = iterate(floor + room);
        int count = countOccurrences(cadena);
        return new WifiPasswordResponse("PASS-" + floor + "-" + room + "-" + count);
    }


    private String iterate(int cant) {
        String cadena = "J";
        for (int i = 0; i < cant; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cadena.length(); j++) {
                char car = cadena.charAt(j);
                switch (car) {
                    case 'J':
                        sb.append("JA");
                        break;
                    case 'A':
                        sb.append("VA");
                        break;
                    case 'V':
                        sb.append("VJ");
                        break;
                }
            }
            cadena = sb.toString();
        }
        return cadena;
    }

    private int countOccurrences(String cadena) {
        return org.springframework.util.StringUtils.countOccurrencesOf(cadena, "JAVA");
    }
}
