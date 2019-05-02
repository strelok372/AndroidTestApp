package com.example.testappl.Sampler;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SamplerRecorder {

    MediaRecorder mediaRecorder;
    final String TAG = "MY_LOG: ";
    File audioFile = null;


    SamplerRecorder() {
    }
    public void startRecord(String name) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mediaRecorder.setAudioEncoder(MediaRecorder.getAudioSourceMax());
        mediaRecorder.setAudioEncodingBitRate(96000);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        if (audioFile == null){
            File path = new File(Environment.getExternalStorageDirectory(), "MyTestSampler");
            audioFile = new File(path, name);
            Log.i(TAG, "Directory: " + audioFile);
            if (!path.exists()){
                boolean p = path.mkdir();
                Log.i(TAG, "Creation of directory: " + p);
            }
            try {
                //audioFile = File.createTempFile("myrecord", ".3gpp", path);
                if (audioFile.exists()){
                    audioFile.delete();
                }
                if (!audioFile.createNewFile()){
                    Log.i(TAG, "Error creating file");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, "error creating file");
            }
            catch (SecurityException s){
                s.printStackTrace();
            }
            Log.i(TAG, "Creation path: " + path.getAbsolutePath());
            Log.i(TAG, "Creation file: " + audioFile.getAbsolutePath());
        }
        mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.i(TAG, "Couldn't prepare.");
            e.printStackTrace();
        }

        mediaRecorder.start();
    }
    public void stopRecord(){
        mediaRecorder.stop();
    }
    public void releaseRecord() {
        mediaRecorder.release();
    }
    public String getFilePath(){
        return audioFile.getAbsolutePath();
    }
}
