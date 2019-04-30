package com.example.testappl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class AlgorithmAdapter extends BaseAdapter {

    private ProgressBar progressBar;
    volatile private ArrayList<ProgressItem> itemList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    final private String TAG = "My log: ";

    @SuppressLint("HandlerLeak")
    private Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "Got message.");
            notifyDataSetChanged();
        }
    };

    AlgorithmAdapter(Context context){
        layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //layoutInflater = getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.lv_item_algo, parent, false);
        }
        progressBar = ((ProgressBar) convertView.findViewById(R.id.progressBar2));
        Log.i(TAG, "Позиция: "+position);
        ProgressItem pi = itemList.get(position);

        progressBar.setMax(pi.max);
        progressBar.setProgress(pi.cur);
        Log.i(TAG, "Element-"+position + " max: " + pi.max + " cur: "+pi.cur);
//        Log.i(TAG, "current pi.max " + pi.max);
//        Log.i(TAG, "current pi.cur " + pi.cur);

        return convertView;
    }

    public void add(final ProgressItem item){
        final int num = itemList.size();
        itemList.add(item);
        new Thread(){
            @Override
            public void run() {
                ProgressItem pi = itemList.get(num);
                for (int i = 0; i < pi.max / 100; i++) {
                    try {
                        pi.cur += 100;
//                        Log.i(TAG, "Current pi = " + pi.cur + " i = " + i);
                        sleep(100);
                    } catch (InterruptedException e) {e.printStackTrace();}
                    //многовато вызывается раз?
                    h.sendEmptyMessage(0);
                }
                //подсчёт хвостика
                pi.cur += (pi.max - pi.cur);

                //удаление необходимого элемента, сдвиг если уже был удалён
                Log.i(TAG, "Удаление элемента " + num);

                if (itemList.size() == num & num > 0) { itemList.remove(num-1); }
                else if (itemList.size() < num) { itemList.remove(itemList.size()-1); }
                else { itemList.remove(num); }

                Log.i(TAG, "Осталось: " + itemList.size() + " потоков.");

                //финальное обновление
                h.sendEmptyMessage(0);
            }
        }.start();
    }
}

class ProgressItem {
    int max = 0;
    int cur = 0;
    ProgressItem(int max){
        this.max = max;
    }
}

