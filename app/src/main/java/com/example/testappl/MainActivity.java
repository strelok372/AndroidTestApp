package com.example.testappl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testappl.Algorithm.Algorithm;
import com.example.testappl.Sampler.Sampler;
import com.example.testappl.Spring.Spring;
import com.example.testappl.StormCalc.StormCalc;

public class MainActivity extends AppCompatActivity {

    private EditText mResultEditText;
    private TextView mInfoTextView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b_toAlgorythm:
                intent = new Intent(this, Algorithm.class);
                startActivity(intent);
                break;
            case R.id.b_toCalculator:
                intent = new Intent(this, StormCalc.class);
                startActivity(intent);
                break;
            case R.id.b_toSampler:
                intent = new Intent(this, Sampler.class);
                startActivity(intent);
                break;
            case R.id.b_toSpring:
                intent = new Intent(this, Spring.class);
                startActivity(intent);
                break;
        }
    }




}