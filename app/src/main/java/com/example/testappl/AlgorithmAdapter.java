package com.example.testappl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmAdapter extends BaseAdapter {

    ProgressBar progressBar;
    //ArrayList<Integer> arrayList;
    private LayoutInflater layoutInflater;
//    private Integer q[];
    final private String TAG = "My log: ";
    ArrayList<Integer> list;

    AlgorithmAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //this.list = list;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //layoutInflater = getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.lv_item_algo, parent, false);
            Log.i(TAG, "New view created!");
        }
        progressBar = ((ProgressBar) convertView.findViewById(R.id.progressBar2));
//        q = list.get(position);
//        progressBar.setMax(q[0]);
        progressBar.setMax(list.get(position));
        //progressBar.setProgress(list.get(position)/2);
        //convertView.
//        Log.i(TAG, "переменная q = " + q[1]);
//        progressBar.setProgress(q[1]);
        //notifyDataSetChanged();
        return convertView;
    }
    public void pbUpdate(){
        progressBar.incrementProgressBy(100);
    }
}
