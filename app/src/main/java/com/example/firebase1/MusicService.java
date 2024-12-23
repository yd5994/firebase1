package com.example.firebase1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service{
    MediaPlayer mediaPlayer;

    public boolean musicPlaying = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){

        mediaPlayer = MediaPlayer.create(this,R.raw.kahoot);
        if(!musicPlaying){

            musicPlaying = true;
            mediaPlayer.start();
        }
        else{

            musicPlaying = false;
            mediaPlayer.stop();
        }
        return super.onStartCommand(intent,flags,startId);

    }

    @Override
    public boolean stopService(Intent name){
        return super.stopService(name);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
