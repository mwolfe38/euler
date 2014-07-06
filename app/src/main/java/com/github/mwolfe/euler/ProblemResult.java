package com.github.mwolfe.euler;

/**
 * Created by mwolfe on 7/5/14.
 */
public class ProblemResult {

    public static final int STATUS_IDLE = 0;
    public static final int STATUS_COMPUTING = 1;
    public static final int STATUS_COMPLETE = 2;
    public final int number;
    public final Result result;
    public final int status;

    public ProblemResult(int number, Result result, int status) {
        this.number = number;
        this.result = result;
        this.status = status;
    }


}
