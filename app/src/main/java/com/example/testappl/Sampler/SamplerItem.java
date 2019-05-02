package com.example.testappl.Sampler;

public class SamplerItem {
    String filename;
    boolean playing = false;
    String path;
    int bar;
//    int state = 0;
    int state = 0;
    SamplerRecorder samplerRecorder;
    SamplerPlayer samplerPlayer;
    SamplerItem(int a){
        filename = a + ".aac";
        samplerRecorder = new SamplerRecorder();
        samplerPlayer = new SamplerPlayer();
        samplerPlayer.setRepeat(bar);
    }

    void setStatus(int status){
        switch (status){
            case 0:
                samplerRecorder.startRecord(filename);
            break;
            case 1:
                samplerRecorder.stopRecord();
                samplerPlayer.setTrack(samplerRecorder.getFilePath());
                samplerRecorder.releaseRecord();
                break;
            case 2:
                samplerPlayer.startPlay();
            break;
            case 3:
                samplerPlayer.pausePlay();
            break;
        }
    }
}
