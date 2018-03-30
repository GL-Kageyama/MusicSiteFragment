package com.swift.android.musicsitefragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ServiceConnection;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp_m;

    //********************************************Service
    //Serviceで応援音声を配置
    private Boolean mIsPlaying;
    private View mBtnPlay;
    private View mBtnStop;
    private BackgroundMusicService mServiceBinder;

    //サービス接続とのコールバック
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder binder) {
            mServiceBinder = ((BackgroundMusicService.MyBinder)binder).getService();
            Log.d("ServiceConnection", "connected");
            updateButtonEnabled();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.d("ServiceConnection", "disconnedted");
            mServiceBinder = null;

        }
    };
    //********************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMusic();

        final EditText input_user = (EditText) findViewById(R.id.main_name_input);
        final EditText input_password = (EditText)findViewById(R.id.main_password_input);
        final Button login_button = (Button)findViewById(R.id.main_login_button);

        final AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(this);

        //ログイン************************************************
        //名前とパスワードを送る************************************

        final EditText editText = (EditText)findViewById(R.id.main_name_input);
        final EditText editText2 = (EditText)findViewById(R.id.main_password_input);

        //Button button = (Button)findViewById(R.id.main_login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //taro 123, jiro 456, hana 789, moe 1011はログイン出来る
                if(input_user.getText().toString().equals("taro") && input_password.getText().toString().equals("123")
                        ||
                        input_user.getText().toString().equals("jiro") && input_password.getText().toString().equals("456")
                        ||
                        input_user.getText().toString().equals("hana") && input_password.getText().toString().equals("789")
                        ||
                        input_user.getText().toString().equals("moe") && input_password.getText().toString().equals("1011")) {
                    Intent intent = new Intent(MainActivity.this, IntroductionActivity.class);
                    if (editText.getText() != null) {
                        String str = editText.getText().toString();
                        intent.putExtra("MESSAGE1", str);
                        editText.setText("");
                    }
                    if (editText2.getText() != null) {
                        String str2 = editText2.getText().toString();
                        intent.putExtra("MESSAGE2", str2);
                        editText.setText("");
                    }
                    Toast.makeText(getApplicationContext(), "目指せなぞなぞマスター！", Toast.LENGTH_SHORT).show();
                    mp_m.stop();
                    startActivity(intent);
                }else{
                    alerDialogBuilder.setTitle("エラー");
                    alerDialogBuilder.setMessage("ユーザー名またはパスワードが違います");
                    alerDialogBuilder.setPositiveButton("OK", null);
                    alerDialogBuilder.show();
                }
            }
        });
        //****************************************************


        //********************************************Service
        mBtnPlay = findViewById(R.id.btn_play);
        mBtnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mServiceBinder != null){
                    mServiceBinder.play();
                }

                updateButtonEnabled();
            }
        });

        mBtnStop = findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mServiceBinder != null){
                    mServiceBinder.stop();
                }
                updateButtonEnabled();
            }
        });
        //********************************************


    }

    public void Newuser(View view){
        Intent intent = new Intent(this, NewuserActivity.class);
        mp_m.stop();
        startActivity(intent);

    }

    public void mainMusic(){
        mp_m = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/n/n99.mp3"));
        mp_m.start();

    }





    //********************************************Service
    private void updateButtonEnabled(){
        if(mServiceBinder != null){
            if(mServiceBinder.isPlaying()){
                mBtnPlay.setEnabled(false);
                mBtnStop.setEnabled(true);
            }else {
                mBtnPlay.setEnabled(true);
                mBtnStop.setEnabled(false);
            }
        }else {
            mBtnPlay.setEnabled(false);
            mBtnStop.setEnabled(false);
        }
    }

    public void doBindService(){
        Intent intent = null;
        intent = new Intent(this, BackgroundMusicService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume(){
        Log.d("activity", "onResume");
        super.onResume();
        if(mServiceBinder == null){
            //サービスにバインド
            doBindService();
        }
        startService(new Intent(getApplicationContext(), BackgroundMusicService.class));
    }

    @Override
    protected void onPause(){
        Log.d("activity", "onPause");
        super.onPause();
        if(mServiceBinder != null){
            mIsPlaying = mServiceBinder.isPlaying();
            if(!mIsPlaying){
                mServiceBinder.stopSelf();
            }
            unbindService(myConnection);
            mServiceBinder = null;
        }
    }
    //********************************************

}








