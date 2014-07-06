package com.github.mwolfe.euler.problems;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem6 extends AbstractProblem {

    public String computeAnswer()  {
        long sumSquares = 0;
        long squareSum = 0;
        for (int i=1; i<=100;i++) {
            sumSquares += Math.pow(i, 2);
            squareSum += i;
        }
        squareSum = (long) Math.pow(squareSum, 2);
        long diff = squareSum - sumSquares;
        return "" + diff;
    }
}
