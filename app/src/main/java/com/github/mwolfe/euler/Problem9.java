package com.github.mwolfe.euler;

/**
 * Created by mwolfe on 7/3/14.
 */
public class Problem9 {

    public static String solve() {

        for (int a=1; a<1000; a++) {
            for (int b=1; b<1000;b++) {

               double cd = Math.sqrt(Math.pow(a,2) + Math.pow(b, 2));
               if (cd % 1 == 0) {
                   int c = (int) cd;
                   if (a + b + c == 1000) {
                       return "" + a*b*c;
                   }
               }


            }
        }
        return "Not found?";
    }
}
