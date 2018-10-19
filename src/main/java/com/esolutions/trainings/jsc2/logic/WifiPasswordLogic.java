package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.web.WifiPasswordResponse;

public class WifiPasswordLogic {

    public WifiPasswordLogic() {
        //Esta vacio por que es un constructor para inicializar en otra clase
    }

    public WifiPasswordResponse calculatePassword(int floor, int room) {
        long count = countOccurrences(floor + room);
        return new WifiPasswordResponse("PASS-" + floor + "-" + room + "-" + count);
    }

    private long countOccurrences(int n) {
        long resul;
        final int opcionResta = 5 % 6;
        final int opcionSuma = 8 % 6;
        switch (n) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                resul = (long) (Math.pow(2, (double) (n - 2)) - Math.pow(-1, (double) (n - 2))) / 3;
        }
        int resto = n % 6;

        switch (resto) {
            case opcionResta:
                return resul - 1;
            case opcionSuma:
                return resul + 1;
            default:
                return resul;
        }
    }
}
