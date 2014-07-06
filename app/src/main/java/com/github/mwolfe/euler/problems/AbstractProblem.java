package com.github.mwolfe.euler.problems;

import com.github.mwolfe.euler.Result;

import java.util.Date;

/**
 * Created by mwolfe on 7/6/14.
 */
public abstract class AbstractProblem {


    public final Result solve() {
        setup();
        Date start = new Date();
        String answer = computeAnswer();
        int ts = (int) ((new Date()).getTime() - start.getTime());
        cleanup();
        return new Result(answer, ts);

    }

    public abstract String computeAnswer();


    public void setup() {}

    public void cleanup() {}


    public boolean isPrime(long num) {
        int max = (int) Math.sqrt(num);
        for (int i=3; i<max; i=i+2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
