package com.github.mwolfe.euler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mwolfe on 7/5/14.
 */
public class ProblemAdapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater mInflator;

    private static final String FIELD_ANSWER = "answer_";
    private static final String FIELD_BENCHMARK_TIME = "benchmark_";
    private static final String FIELD_RESULT_STATUS = "status_";

    private ArrayList<ProblemResult> problemResults = new ArrayList<ProblemResult>();

    private Context mContext;

    SharedPreferences mSavedAnswers;


    private BroadcastReceiver myReceiver = new  BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ProblemSolverService.ACTION_PROBLEM_SOLVED)) {

            } else if (intent.getAction().equals(ProblemSolverService.ACTION_PROBLEM_SOLVE_STARTED)) {

            }
        }
    };

    @Override
    public void onClick(View view) {
        ProblemResult result = (ProblemResult) view.getTag();
        view.setEnabled(false);
        solveProblem(result.number);
    }

    private void solveProblem(int problem) {
        Intent problemSolver = new Intent(mContext, ProblemSolverService.class);
        problemSolver.putExtra(ProblemSolverService.EXTRA_PROBLEM_NUMBER, problem);
        mContext.startService(problemSolver);
    }

    class ViewHolder {
        public TextView resultView, timeView, problemNumberView;
        public ProgressBar progressView;
        public Button solveButton;
    }


    public ProblemAdapter(Context context) {

        mContext = context;
        mSavedAnswers = context.getSharedPreferences("answers", Context.MODE_PRIVATE);

        mInflator = LayoutInflater.from(context.getApplicationContext());

        for (int i=1; i<50; i++) {
            try {
                Class klass = Class.forName("com.github.mwolfe.euler.problems.Problem" + i);
                addProblemResultInternal(problemResultFromStorage(i));
            } catch (ClassNotFoundException e) {

            }

        }
    }

    private ProblemResult problemResultFromStorage(int problemNumber) {
        int status = mSavedAnswers.getInt(FIELD_RESULT_STATUS + problemNumber, ProblemResult.STATUS_IDLE);
        String answer = mSavedAnswers.getString(FIELD_ANSWER + problemNumber, null);
        int ts = mSavedAnswers.getInt(FIELD_BENCHMARK_TIME + problemNumber, 0);
        return new ProblemResult(problemNumber, new Result(answer, ts), status);
    }

    private void addProblemResultInternal(ProblemResult result) {
        problemResults.add(result);
    }

    public void addResult(int problem, Result result, int status) {
        SharedPreferences.Editor editor = mSavedAnswers.edit();
        editor.putInt(FIELD_RESULT_STATUS + problem, status);
        if (result == null) {
            editor.putString(FIELD_ANSWER + problem, null);
            editor.putInt(FIELD_BENCHMARK_TIME + problem, 0);
        } else {
            editor.putString(FIELD_ANSWER + problem, result.answer);
            editor.putInt(FIELD_BENCHMARK_TIME + problem, result.benchmarkTimeMs);
        }

        editor.commit();
        ProblemResult newProbResult = new ProblemResult(problem, result, status);
        if (problem > problemResults.size()) {
            problemResults.add(newProbResult);
        } else {
            for (int i = 0; i < problemResults.size(); i++) {
                ProblemResult probResult = problemResults.get(i);
                if (probResult.number == problem) {
                    problemResults.set(i, newProbResult);
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return problemResults.size();
    }

    @Override
    public Object getItem(int i) {
        return problemResults.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        ProblemResult thisProbResult = (ProblemResult) getItem(position);
        if (view == null) {
            view = mInflator.inflate(R.layout.problem_list_item, parent, false);
            holder = new ViewHolder();
            holder.problemNumberView = (TextView) view.findViewById(R.id.ProblemNumber);
            holder.resultView = (TextView) view.findViewById(R.id.ProblemResult);
            holder.progressView = (ProgressBar) view.findViewById(R.id.Progress);
            holder.timeView = (TextView) view.findViewById(R.id.BenchmarkTime);
            holder.solveButton = (Button) view.findViewById(R.id.SolveButton);
            view.setTag(holder);
        } else {
            holder =  (ViewHolder) view.getTag();
        }


        holder.solveButton.setOnClickListener(this);
        holder.solveButton.setTag(thisProbResult);
        ProblemResult result = (ProblemResult) getItem(position);
        holder.problemNumberView.setText("" + result.number + ".");
        holder.progressView.setVisibility(View.GONE);
        holder.solveButton.setEnabled(true);
        holder.solveButton.setVisibility(View.VISIBLE);
        holder.timeView.setText("");
        if (result.status == ProblemResult.STATUS_IDLE) {
            holder.resultView.setText("?");
        } else {
            if (result.status == ProblemResult.STATUS_COMPUTING) {
                holder.progressView.setVisibility(View.VISIBLE);
                holder.solveButton.setEnabled(false);
                holder.solveButton.setVisibility(View.GONE);
                holder.timeView.setText("...");
                holder.resultView.setText("");
            } else {
                holder.timeView.setText("" + result.result.benchmarkTimeMs + "ms");
                holder.resultView.setText(result.result.answer);
            }
        }

        return view;
    }
}
