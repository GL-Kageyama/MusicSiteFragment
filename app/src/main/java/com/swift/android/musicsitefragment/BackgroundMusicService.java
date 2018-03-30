package com.swift.android.musicsitefragment;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


//******************************************************

public class BackgroundMusicService extends Service {

    private static final String TAG = BackgroundMusicService.class.getSimpleName();

    private final IBinder mBinder = new MyBinder();
    private MediaPlayer mPlayer;

    public BackgroundMusicService(){
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d("service", "onBind");
        return mBinder;
    }

    public class MyBinder extends Binder{
        BackgroundMusicService getService(){
            return BackgroundMusicService.this;
        }
    }

    //再生
    public void play() {
        Log.d(TAG, "play");
        mPlayer = MediaPlayer.create(this,
                Uri.parse("https://soundeffect-lab.info/sound/voice/mp3/line-girl1/line-girl1-ganbare1.mp3"));
        mPlayer.setLooping(true);
        mPlayer.setVolume(100, 100);
        mPlayer.start();

    }

    //停止
    public void stop() {
        Log.d(TAG, "stop");
        if(mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    //再生中が確認
    public boolean isPlaying() {
        boolean isPlaying = false;
        if(mPlayer != null){
            isPlaying = mPlayer.isPlaying();
        }
        return isPlaying;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        return START_NOT_STICKY;
    }
}
//******************************************************
