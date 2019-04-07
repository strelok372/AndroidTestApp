package com.example.testappl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class Algorithm extends AppCompatActivity {

    ListView listView;
    final private String TAG = "My log: ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorythm_dialog);
        listView = (ListView) findViewById(R.id.lv_something);
        //View view = new TextView(this);
        //((TextView) view).setText("sfdfw");
        //listView.addView(view);
    }

    public void onClick(View v){
        new MyRunnable(); // создаём новый поток

        try {
            for (int i = 5; i > 0; i--) {
                Log.i(TAG, "Главный поток: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Log.i(TAG, "Главный поток прерван");
        }
    }

    class MyRunnable implements Runnable {
        Thread thread;
        // Конструктор
        MyRunnable() {
            // Создаём новый второй поток
            thread = new Thread(this, "Поток для примера");
            Log.i(TAG, "Создан второй поток " + thread);
            thread.start(); // Запускаем поток
        }

        // Обязательный метод для интерфейса Runnable
        public void run() {
            try {
                for (int i = 5; i > 0; i--) {
                    Log.i(TAG, "Второй поток: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Log.i(TAG, "Второй поток прерван");
            }
        }
    }
}
