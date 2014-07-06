package com.github.mwolfe.euler.problems;
/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem7 extends AbstractProblem {


    public String computeAnswer()  {
        long curPrime = 3;
        int primeCount = 2;
        for (long i=5; i<Long.MAX_VALUE; i+=2) {
            int max = (int) Math.sqrt(i);
            boolean isPrime = true;
            for (int j=Math.min(max, 3); j<=max; j+=2) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                curPrime = i;
                primeCount++;
            }
            if (primeCount == 10001) {
                break;
            }
        }

        return "" + curPrime;

    }
}
