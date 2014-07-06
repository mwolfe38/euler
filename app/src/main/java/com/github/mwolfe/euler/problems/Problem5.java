package com.github.mwolfe.euler.problems;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem5 extends AbstractProblem {

    public String computeAnswer()  {
        boolean found = false;
        long currentHigh = 2520;
        long i = currentHigh;
        long divisorHigh = 10;
        while (!found && i < Long.MAX_VALUE) {
            i+=currentHigh;
            for (long j=divisorHigh; j<=20; j++) {
                if (i % j != 0) {
                    break;
                }
                if (j > divisorHigh) {
                    divisorHigh = j;
                    currentHigh = i;
                }
            }
            if (divisorHigh >= 20) {
                found = true;
            }
        }
        return "" + i;
    }
}
