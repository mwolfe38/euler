package com.github.mwolfe.euler;

import android.app.IntentService;
import android.content.Intent;

import com.github.mwolfe.euler.problems.AbstractProblem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mwolfe on 7/5/14.
 */
public class ProblemSolverService extends IntentService {


    public static final String ACTION_PROBLEM_SOLVED = "com.github.mwolfe.euler.problem_solved";
    public static final String ACTION_PROBLEM_SOLVE_STARTED = "com.github.mwolfe.euler.problem_solve_started";
    public static final String EXTRA_PROBLEM_NUMBER = "problem_number";
    public static final String EXTRA_PROBLEM_RESULT = "problem_result";

    public ProblemSolverService() {
        super(ProblemSolverService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int problemNumber = intent.getIntExtra(EXTRA_PROBLEM_NUMBER, 1);
        Result result = null;
        try {
            Class klass = Class.forName("com.github.mwolfe.euler.problems.Problem" + problemNumber);
            AbstractProblem problem = (AbstractProblem) klass.newInstance();
            broadcastProblemStarted(problemNumber);
            result = problem.solve();
        } catch (ClassNotFoundException e) {
            result = null;//break out if this problem doesn't exist yet
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        broadcastResult(problemNumber, result);
    }

    private void broadcastProblemStarted(int problem) {
        Intent broadcast = new Intent();
        broadcast.setAction(ACTION_PROBLEM_SOLVE_STARTED);
        broadcast.addCategory(Intent.CATEGORY_DEFAULT);
        broadcast.putExtra(EXTRA_PROBLEM_NUMBER, problem);
        sendBroadcast(broadcast);
    }

    private void broadcastResult(int problem, Result result) {
        Intent broadcast = new Intent();
        broadcast.setAction(ACTION_PROBLEM_SOLVED);
        broadcast.addCategory(Intent.CATEGORY_DEFAULT);
        broadcast.putExtra(EXTRA_PROBLEM_NUMBER, problem);
        broadcast.putExtra(EXTRA_PROBLEM_RESULT, result);
        sendBroadcast(broadcast);
    }
}
