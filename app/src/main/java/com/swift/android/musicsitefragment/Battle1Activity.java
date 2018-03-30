package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class Battle1Activity extends AppCompatActivity implements Fragment1Activity.onFragmentInteractionListener{

    private MediaPlayer mp1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle1);

        battle1Music();

    }

    @Override
    public void onFragmentInteraction(){
        Toast.makeText(getApplicationContext(), "びっくり人間なら食えるかもな", Toast.LENGTH_SHORT).show();
    }

    public void battle1Button1(View view){
        Intent intent = new Intent(this, Battle2Activity.class);
        Toast.makeText(getApplicationContext(), "正解だ　固いからな", Toast.LENGTH_SHORT).show();
        mp1.stop();
        startActivity(intent);
    }

    public void battle1Button2(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "食パンなわけないだろ", Toast.LENGTH_SHORT).show();
        mp1.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);
    }

    public void battle1Button3(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "あんパンは食べ物だぞ", Toast.LENGTH_SHORT).show();
        mp1.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);
    }

    public void battle1Music(){
        mp1 = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/n/n4.mp3"));
        mp1.start();
    }

}
