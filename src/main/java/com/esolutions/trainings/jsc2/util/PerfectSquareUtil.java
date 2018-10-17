package com.esolutions.trainings.jsc2.util;

public class PerfectSquareUtil {
    private PerfectSquareUtil() {
    }

    public static boolean isPerfectSquare(long n) {
        return ((int)(Math.sqrt(n)) * (int)(Math.sqrt(n)) == n);
    }
}
