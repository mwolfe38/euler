package com.github.mwolfe.euler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class MyActivity extends Activity {

    ListView lv;

    ProblemAdapter mAdapter;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mAdapter != null) {
                String action = intent.getAction();
                int problem = intent.getIntExtra(ProblemSolverService.EXTRA_PROBLEM_NUMBER, 0);
                if (action.equals(ProblemSolverService.ACTION_PROBLEM_SOLVED)) {
                    Result result = intent.getParcelableExtra(ProblemSolverService.EXTRA_PROBLEM_RESULT);
                    mAdapter.addResult(problem, result, ProblemResult.STATUS_COMPLETE);
                } else if (action.equals(ProblemSolverService.ACTION_PROBLEM_SOLVE_STARTED)) {
                    mAdapter.addResult(problem, null, ProblemResult.STATUS_COMPUTING);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        lv = (ListView) findViewById(R.id.ListView);
        mAdapter = new ProblemAdapter(this.getApplicationContext());
        lv.setAdapter(mAdapter);

    }



    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ProblemSolverService.ACTION_PROBLEM_SOLVE_STARTED);
        filter.addAction(ProblemSolverService.ACTION_PROBLEM_SOLVED);
        filter.addCategory(Intent.CATEGORY_DEFAULT);

        registerReceiver(mReceiver, filter);
    }


    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
