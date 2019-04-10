package com.example.testappl;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

public class Algorithm extends AppCompatActivity {

    static Handler h;
    final private String TAG = "My log: ";
    public int a;
    AlgorithmAdapter progBarAdapter;
    ListView listView;
    //ArrayList<HashMap<Integer, Integer>> list;
//    ArrayList<Integer[]> list;
    //HashMap<ArrayList<Integer>, Integer> list;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorythm_dialog);
        listView = (ListView) findViewById(R.id.lv_something);


//        list = new ArrayList<Integer[]>();

        //progBarAdapter = new AlgorithmAdapter(this, list);
        progBarAdapter = new AlgorithmAdapter(this);
        listView.setAdapter(progBarAdapter);

        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "Got message.");
                progBarAdapter.notifyDataSetChanged();
            }
        };
    }

    public void onClick(View v) {
        Random random = new Random();
        //Thread thread = new Thread(new MyRunnable(random.nextInt(5000)));
        Asyn asyn = new Asyn(random.nextInt(5000));
        //thread.start();
        asyn.execute();
    }

    class MyRunnable implements Runnable {

        int life;
        Integer[] p;
//        int number;

        MyRunnable(int lifeTime) {
//            number = list.size();
            life = lifeTime;
            p = new Integer[] {lifeTime, 0};
        }

        public void run() {
            try {
                Log.i(TAG, "Случайное число: " + life);
//                list.add(p);
                for (int i = 0; i < life / 100; i++) {
                    Log.i(TAG, "Жизнь потока: " + i);
                    //Thread.sleep(200);
                    TimeUnit.MILLISECONDS.sleep(200);
                    p[1] += 100;
                    //list.set(number, p);
                    Log.i(TAG, "Переменная p = " + p[1]);
                    //listView.getAdapter().getView(number,listView.getChildAt(number),listView);
                    h.sendEmptyMessage(0);
                    //progBarAdapter.progressHandler.sendEmptyMessage(1);
                }
                //list.remove(number);

            } catch (InterruptedException e) {
                Log.i(TAG, "Второй поток прерван");
            }
        }
    }

    class Asyn extends AsyncTask<Integer, Integer, Integer> {

        int life;
//        Integer[] p;
//        int number;

        Asyn(int lifeTime){
//            number = list.size();
            life = lifeTime;
//            p = new Integer[] {lifeTime, 0};
        }
        @Override
        protected Integer doInBackground(Integer... integers) {
//            list.add(p);
            progBarAdapter.list.add(life);
            for (int i = 0; i < life/100; i++) {
                try {









                        Log.i(TAG, "Случайное число: " + life);
                        Log.i(TAG, "Жизнь потока: " + i);
                        Thread.sleep(200);
//                        TimeUnit.MILLISECONDS.sleep(200);
//                        p[1] += 100;
//                        list.set(number, p);
//                        Log.i(TAG, "Переменная p = " + p[1]);
                        publishProgress(0);
//                        listView.getAdapter().getView(number,listView.getChildAt(number),listView);
//                        h.sendEmptyMessage(0);
//                        progBarAdapter.progressHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //progBarAdapter.progressBar.incrementProgressBy(100);
            //progBarAdapter.pbUpdate();
            progBarAdapter.notifyDataSetChanged();
            //((ProgressBar)progBarAdapter.getItem(0)).incrementProgressBy(100);
//            h.sendEmptyMessage(0);
        }
    }
}
