package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Battle4Activity extends AppCompatActivity implements Fragment4Activity.onFragmentInteractionListener{

    private MediaPlayer mp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle4);

        battle4Music();

    }

    @Override
    public void onFragmentInteraction(){
        Toast.makeText(getApplicationContext(), "しゃぶしゃぶかもしれないぞ", Toast.LENGTH_SHORT).show();
    }

    public void battle4Button1(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "ふふ、引っかかったな", Toast.LENGTH_SHORT).show();
        mp4.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle4Button2(View view){
        Intent intent = new Intent(this, Battle5Activity.class);
        Toast.makeText(getApplicationContext(), "酢入り（推理）よくわかったな", Toast.LENGTH_SHORT).show();
        mp4.stop();
        startActivity(intent);

    }

    public void battle4Button3(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "ふん、出直して来い", Toast.LENGTH_SHORT).show();
        mp4.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle4Music(){
        mp4 = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/n/n36.mp3"));
        mp4.start();
    }

}
