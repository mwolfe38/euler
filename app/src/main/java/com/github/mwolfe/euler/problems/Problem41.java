package com.github.mwolfe.euler.problems;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem41 extends AbstractProblem {


    static long charArrayToLong(char[] chars) {
        long sum = 0;
        for (int i=0; i<chars.length; i++) {
            sum += Character.getNumericValue(chars[i]) * Math.pow(10, chars.length - 1 - i);
        }
        return sum;
    }

    public String computeAnswer()  {


        char[] chars = {'9','8','7','6','5','4','3','2','1'};
        int pivotidx=8, pivotdistance=1;
        int lastIndex = 9;

        boolean done = false;
        long testValue = 3;
        while (!done) {
            testValue = charArrayToLong(chars);
            if (isPrime(testValue)) {
                done = true;
            } else {

                chars = new char[] {'9','8','7','6','5','4','3','2','1'};
                char tmp = chars[pivotidx];
                chars[pivotidx] = chars[pivotidx - pivotdistance];
                chars[pivotidx - pivotdistance] = tmp;
                if (pivotidx - pivotdistance <= 0) {
                    pivotidx = 8;
                    pivotdistance++;
                } else {
                    pivotidx -= pivotdistance;
                }
            }
        }

        int len = chars.length;

        char tmp;
        for (int i=chars.length-1; i>0; i--) {

            for (int j=chars.length-2; j>0; j--) {

                for (int k=j; j<len; k--) {

                }

            }

        }

        return "" + testValue;
    }
}
