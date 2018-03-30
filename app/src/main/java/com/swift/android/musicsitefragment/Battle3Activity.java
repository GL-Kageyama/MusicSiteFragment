package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Battle3Activity extends AppCompatActivity implements Fragment3Activity.onFragmentInteractionListener {

    private MediaPlayer mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle3);

        battle3Music();

    }

    @Override
    public void onFragmentInteraction(){
        Toast.makeText(getApplicationContext(), "プレゼント フォー ユーじゃないぞ", Toast.LENGTH_SHORT).show();
    }

    public void battle3Button1(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "貰っても困るわ", Toast.LENGTH_SHORT).show();
        mp3.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle3Button2(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "どうやって貰うんだよ", Toast.LENGTH_SHORT).show();
        mp3.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void battle3Button3(View view){
        Intent intent = new Intent(this, Battle4Activity.class);
        Toast.makeText(getApplicationContext(), "日が暮れた、なんつって", Toast.LENGTH_SHORT).show();
        mp3.stop();
        startActivity(intent);

    }

    public void battle3Music(){
        mp3 = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/n/n41.mp3"));
        mp3.start();
    }

}
