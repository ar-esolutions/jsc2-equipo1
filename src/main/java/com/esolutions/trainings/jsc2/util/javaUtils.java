package com.esolutions.trainings.jsc2.util;

public class JavaUtils {
    private JavaUtils() {
    }

    public static boolean isPerfectSquare(long n) {
        return ((int)(Math.sqrt(n)) * (int)(Math.sqrt(n)) == n);
    }
}
