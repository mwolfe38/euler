package com.github.mwolfe.euler.problems;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem10 extends AbstractProblem {

    public String computeAnswer()  {
        long sum = 10;

        for (long i=7; i<2000000; i+=2) {
            int max = (int) Math.sqrt(i);
            boolean isPrime = true;
            for (int j=3; j<=max; j+=2) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                sum += i;
            }

        }

        return "" + sum;

    }
}
