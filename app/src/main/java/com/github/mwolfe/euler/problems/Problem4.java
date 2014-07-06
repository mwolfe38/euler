package com.github.mwolfe.euler.problems;
/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem4 extends AbstractProblem {


    public static boolean isPalindrome(int num) {
        int revNum = 0;
        int orig = num;
        while (num > 0) {
            int rem = num % 10;
            revNum = (revNum * 10) + rem;
            num = num / 10;
        }
        return orig == revNum;
    }

    public String computeAnswer() {
        int largest = 0, product = 0;
        for (int i=100; i<1000;i++) {

            for (int j=i; j<1000; j++) {
                product = i * j;
                if (product > largest) {
                    if (isPalindrome(product)) {
                        largest = product;
                    }
                }
            }
        }

        return "" + largest;
    }
}
