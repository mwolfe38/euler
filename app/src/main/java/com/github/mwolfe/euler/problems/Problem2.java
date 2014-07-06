package com.github.mwolfe.euler.problems;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem2 extends AbstractProblem {

    public String computeAnswer() {
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
