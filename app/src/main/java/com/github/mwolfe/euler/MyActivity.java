package com.github.mwolfe.euler;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MyActivity extends Activity {

    ListView lv;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        lv = (ListView) findViewById(R.id.ListView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lv.setAdapter(mAdapter);
        ProblemSolverBackgroundTask task = new ProblemSolverBackgroundTask();
        task.execute();
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


    private static String[] cachedAnswers = new String[50];

    public class ProblemSolverBackgroundTask extends AsyncTask<Void, String, Void>
    {
        @Override
        public void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            for (int i=0; i<cachedAnswers.length;i++) {
                if (cachedAnswers[i] == null) {
                    try {
                        Class<?> klass = Class.forName("com.github.mwolfe.euler.Problem" + (i+1));
                        Method m = klass.getMethod("solve");
                        cachedAnswers[i] = (String) m.invoke(null);

                    } catch (ClassNotFoundException e) {
                        //haven't yet solved
                        break;
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                publishProgress("" + (i+1) + ". " + cachedAnswers[i]);
            }
            return null;
        }

        @Override
        public void onProgressUpdate(String ... updates) {
            for (String update : updates) {
                mAdapter.add(update);
            }
        }

        @Override
        public void onPostExecute(Void result) {
            setProgressBarIndeterminateVisibility(false);
        }
    }
}
