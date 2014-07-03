package com.github.mwolfe.euler;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem2 {

    public static String solve() {
        int prev=1,fib=2, tmp;
        int sum = 0;
        do  {
            if (fib % 2 == 0) {
               sum += fib;
            }
            tmp = fib;
            fib = fib + prev;
            prev = tmp;
        } while (fib <= 4000000);

        return "" + sum;
    }
}
