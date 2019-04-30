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
import android.support.v7.widget.SwitchCompat;
import android.util.ArraySet;
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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    Switch switchCompat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorythm_dialog);
        listView = (ListView) findViewById(R.id.lv_something);
        progBarAdapter = new AlgorithmAdapter(this);
        listView.setAdapter(progBarAdapter);
        switchCompat = (Switch) findViewById(R.id.sw_thread);
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
    }

    public void onClick(View v) {
        final Random random = new Random();
        if(switchCompat.isChecked()){
            new Thread(){
                @Override
                public void run() {
                    while (switchCompat.isChecked()){
                        //int a = random.nextInt(10000);
                        progBarAdapter.add(new ProgressItem(random.nextInt(10000)));
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        else {
            final int a = random.nextInt(10000);
            progBarAdapter.add(new ProgressItem(a));
        }

    }
}
