package com.esolutions.trainings.jsc2.util;

public class javaUtils {

    public javaUtils() {
    }

    public static boolean isPerfectSquare(long n) {
        /*
        double n1= Math.sqrt(n);
        double n2= Math.sqrt(n);
        System.out.println("n1 : "+ n1);
        System.out.println("n2 : "+ n2);
        System.out.println(n1*n2);
        */
        return ((Math.sqrt(n)) * (Math.sqrt(n)) == n);
    }
}
