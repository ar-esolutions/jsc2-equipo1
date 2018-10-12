package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.web.WifiPasswordResponse;

public class WifiPasswordLogic {
    private int floor;
    private int room;

    public WifiPasswordResponse calculatePassword(int floor, int room) {
        String cadena = iterate(floor + room);
        int count = countOccurrences(cadena);
        return new WifiPasswordResponse("PASS-" + floor + "-" + room + "-" + count);
    }

    public WifiPasswordLogic() {
    }

    public WifiPasswordLogic(int floor, int room) {
        this.floor = floor;
        this.room = room;
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
            System.out.println(cadena);
        }
        return cadena;
    }

    private int countOccurrences(String cadena){
        /*int count = 0;
        int j = 4;
        for (int i = 0; i < cadena.length() - 3; i++) {
            String subcadena = cadena.substring(i, j);
            if(subcadena.equals("JAVA")) {
                count++;
            }
            j++;
        }
        return count;
        */
        return org.springframework.util.StringUtils.countOccurrencesOf(cadena, "JAVA");
    }
}
