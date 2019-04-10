package com.example.testappl;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StormCalc extends AppCompatActivity {

    String reactive[] = {"Камфара (C10H16O)(г): ", "Калиевая селитры (нитрат калия, KNO3)(г): ", "Нашатырь (хлорид аммония, NH4Cl)(г): ", "Вода(мл): ", "Этиловый спирт(мл): "};
    String result[] = {"0","0","0","0","0"};
    Double coef[] = {10.0/73, 2.5/73, 2.5/73, 33.0/73, 40.0/73};

    ListView lv;
    CustomAdapter customAdapter;
String temp;
EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storm_calc);
        setTitle("Штормгласс калькулятор");

        et = (EditText) findViewById(R.id.et_writeVolume);
        lv = (ListView) findViewById(R.id.lv_storm);

        customAdapter = new CustomAdapter();
        lv.setAdapter(customAdapter);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {            }

            @Override
            public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
                    CalculateStorm(s);
                    customAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {            }
        };
        et.addTextChangedListener(textWatcher);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(R.string.storm_desc);
        b.setCancelable(true);
        b.show();
        //new Intent(this, )
        //inflater.inflate()
        return true;
    }

    void CalculateStorm(CharSequence volume){
        if (volume.length() != 0) {

            Double vol = Double.parseDouble(volume.toString());
            double exp;

            for (int i = 0; i<5; i++){
                exp = coef[i] * vol;
                result[i] = String.format("%.2f",exp);
            }
        }
    }


    class CustomAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        @Override
        public int getCount() {
            return reactive.length;
        }

        @Override
        public Object getItem(int position) {
            return reactive[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(convertView == null){
                layoutInflater = getLayoutInflater();
                view = layoutInflater.inflate(R.layout.lv_item_storm, parent, false);
            }
            ((TextView)view.findViewById(R.id.tv_descrip)).setText(reactive[position]);
            ((TextView)view.findViewById(R.id.tv_val)).setText(result[position]);
            return view;
        }
    }


}

