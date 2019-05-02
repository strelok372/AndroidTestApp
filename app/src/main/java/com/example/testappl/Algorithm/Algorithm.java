package com.example.testappl.Algorithm;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

import com.example.testappl.R;

import java.util.Random;

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
