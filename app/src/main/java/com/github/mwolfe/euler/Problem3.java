package com.github.mwolfe.euler;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem3 {

    public static boolean isPrime(long num) {
        int max = (int) Math.sqrt(num);
        for (int i=3; i<max; i=i+2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static String solve() {
        long num = 600851475143L;
        int max = (int) Math.sqrt(num);
        long largest = 1;
        for (int i=3; i<max; i+=2) {
            if (num % i == 0) {
                if (isPrime(i)) {
                    largest = i;
                }
            }
        }
        return "" + largest;
    }
}
