package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Battle2Activity extends AppCompatActivity implements Fragment2Activity.onFragmentInteractionListener{

    private MediaPlayer mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle2);

        battle2Music();

    }

    @Override
    public void onFragmentInteraction(){
        Toast.makeText(getApplicationContext(), "本当にやせるけどな", Toast.LENGTH_SHORT).show();
    }

    public void battle2Button1(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "コレステロール値上がるぞ", Toast.LENGTH_SHORT).show();
        mp2.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle2Button2(View view){
        Intent intent = new Intent(this, Battle3Activity.class);
        Toast.makeText(getApplicationContext(), "ガリガリになっちゃうからな", Toast.LENGTH_SHORT).show();
        mp2.stop();
        startActivity(intent);

    }

    public void battle2Button3(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "栄養あるっつーの", Toast.LENGTH_SHORT).show();
        mp2.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle2Music(){
        mp2 = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/o/o4.mp3"));
        mp2.start();
    }

}
