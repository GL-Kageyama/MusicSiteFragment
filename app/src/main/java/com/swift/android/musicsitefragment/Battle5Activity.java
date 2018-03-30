package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Battle5Activity extends AppCompatActivity implements Fragment5Activity.onFragmentInteractionListener{

    private MediaPlayer mp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle5);

        battle5Music();

    }

    @Override
    public void onFragmentInteraction(){
        Toast.makeText(getApplicationContext(), "「やっぱり」な絵って何だ？", Toast.LENGTH_SHORT).show();

    }

    public void battle5Button1(View view){
        Intent intent = new Intent(this, ClearActivity.class);
        Toast.makeText(getApplicationContext(), "貴様は、なぞなぞマスターだ！", Toast.LENGTH_SHORT).show();
        mp5.stop();
        startActivity(intent);

    }

    public void battle5Button2(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "ゾウとかで　いいんじゃね？", Toast.LENGTH_SHORT).show();
        mp5.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle5Button3(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "石とか　あるじゃん？", Toast.LENGTH_SHORT).show();
        mp5.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle5Music(){
        mp5 = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/n/n65.mp3"));
        mp5.start();
    }

}
