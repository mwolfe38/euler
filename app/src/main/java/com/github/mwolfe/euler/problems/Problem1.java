package com.github.mwolfe.euler.problems;

import com.github.mwolfe.euler.Result;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem1 extends AbstractProblem {


    public String computeAnswer() {
        int answer = 0;
        for (int i=0; i<1000; i++) {
            if (i%3 == 0 || i%5 == 0) {
                answer += i;
            }
        }
        return "" + answer;
    }
}
