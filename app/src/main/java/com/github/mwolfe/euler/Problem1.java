package com.github.mwolfe.euler;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem1 {
    public static String solve() {
        int answer = 0;
        for (int i=0; i<1000; i++) {
            if (i%3 == 0 || i%5 == 0) {
                answer += i;
            }
        }
        return "" + answer;
    }
}
