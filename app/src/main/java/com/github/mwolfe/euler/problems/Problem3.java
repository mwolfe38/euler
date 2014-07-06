package com.github.mwolfe.euler.problems;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem3 extends AbstractProblem {

    public String computeAnswer() {
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
