package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ClearActivity extends AppCompatActivity {

    private MediaPlayer mp_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        clearMusic();

    }

    public void clearButton(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        Toast.makeText(getApplicationContext(), "さすが勇者、もう一度ね", Toast.LENGTH_SHORT).show();
        mp_c.stop();
        String str = "aaa";
        intent.putExtra("MESSAGE1", str);
        startActivity(intent);

    }

    public void clearMusic(){
        mp_c = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/c/c11.mp3"));
        mp_c.start();

    }

}
