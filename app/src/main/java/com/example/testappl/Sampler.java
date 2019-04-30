package com.example.testappl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Sampler extends AppCompatActivity {
    //    SoundPool soundPool;
    RecyclerView recyclerView;
    final String TAG = "MY_LOG: ";
    ImageView imageView;
    int a = 0;
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    String[] scroll = {"1/8", "1/4", "1/2", "1"};
    int step[] = {500,1000,2000,4000};
    SamplerRecorder samplerRecorder;
    SamplerPlayer samplerPlayer;
    SamplerAdapter samplerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampler);
        imageView = findViewById(R.id.iv_sampler);
        samplerRecorder = new SamplerRecorder();
        spinner = findViewById(R.id.spinner);

        //спиннер
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, scroll);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                samplerPlayer.setRepeat(step[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //проигрыватель
        samplerPlayer = new SamplerPlayer();

        checkPermissions();

        samplerAdapter = new SamplerAdapter();
        recyclerView = findViewById(R.id.rv_sampl);
        recyclerView.setAdapter(samplerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.set

    }

    @Override
    protected void onStop() {
        super.onStop();
        samplerPlayer.releasePlayer();
        samplerRecorder.releaseRecord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        samplerPlayer.stopPlay();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sampler:
                switch (a) {
                    case 0:
                        samplerRecorder.startRecord("test.aac");
                        imageView.setImageResource(R.drawable.ic_stop);
                        a++;
                        break;
                    case 1:
                        samplerRecorder.stopRecord();
//                        Log.i(TAG, "Setting player path to: " + samplerRecorder.getFilePath());
//                        String h = ;
                        samplerPlayer.setTrack(samplerRecorder.getFilePath());
                        imageView.setImageResource(R.drawable.ic_play);
                        a++;
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.ic_pause);
                        samplerPlayer.startPlay();
                        a++;
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.ic_play);
                        samplerPlayer.pausePlay();
                        a--;
                        break;
                }
                break;
            case R.id.b_clear_sampler:
                imageView.setImageResource(R.drawable.ic_record);
                samplerPlayer.stopPlay();
                a = 0;
                break;
            case R.id.b_add_sampler:
                samplerAdapter.add();
                samplerAdapter.notifyDataSetChanged();
        }
    }

    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            imageView.setClickable(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            imageView.setClickable(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    imageView.setClickable(true);
                }
                break;
        }
    }

    class mySpinner {

    }
}
