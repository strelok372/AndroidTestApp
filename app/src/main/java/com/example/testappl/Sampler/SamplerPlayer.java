package com.example.testappl.Sampler;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class SamplerPlayer {
    final String TAG = "SPlayer log: ";
    MediaPlayer mediaPlayer;
    boolean repeatable = false;
    int repeat;
    Thread run;

    SamplerPlayer() {
        mediaPlayer = new MediaPlayer();
        Log.i(TAG, "MediaPlayer created!");
    }

    void startPlay() {
        repeatable = true;
        run = new Thread("play"){
            @Override
            public void run() {
                //super.run();
                while (repeatable) {
                    mediaPlayer.start();
                    try {
                        sleep(repeat);
                        //mediaPlayer.stop();
                        mediaPlayer.seekTo(0);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        run.start();
    }

    void stopPlay() {
        repeatable = false;
        mediaPlayer.stop();
        if(run != null) { run.interrupt(); }
    }

    void pausePlay(){
        mediaPlayer.pause();
        repeatable = false;
        run.interrupt();
    }

    void setTrack(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            Log.i(TAG, "Error setting data source");
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.i(TAG, "Error preparing or starting");
            e.printStackTrace();
        }
    }

    void setRepeat(final int a) {
        repeat = a;
    }

    void releasePlayer() {
        mediaPlayer.stop();
        run.interrupt();
        mediaPlayer.release();
    }
}
