package com.swift.android.musicsitefragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class NewuserActivity extends AppCompatActivity {

    private MediaPlayer mp_n;

    public void onClickButton(View v){

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);

        newuserMusic();

        final EditText editText = (EditText)findViewById(R.id.new_name_input);
        final EditText editText2 = (EditText)findViewById(R.id.new_password_input);
        final EditText editText3 = (EditText)findViewById(R.id.new_character_input);

        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.new_radiogroup);


        final Button new_button = (Button)findViewById(R.id.new_button);




        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewuserActivity.this, IntroductionActivity.class);
                if(editText.getText() != null){
                    String str = editText.getText().toString();
                    intent.putExtra("MESSAGE1", str);
                    editText.setText("");
                }
                if (editText2.getText() != null){
                    String str2 = editText2.getText().toString();
                    intent.putExtra("MESSAGE2", str2);
                    editText2.setText("");
                }
                if (editText3.getText() != null){
                    String str3 = editText3.getText().toString();
                    intent.putExtra("MESSAGE3", str3);
                    editText3.setText("");
                }
                mp_n.stop();
                startActivity(intent);
            }
        });
    }

    public void newDateInput(View view){
        Intent intent = new Intent(this, IntroductionActivity.class);
        mp_n.stop();
        startActivity(intent);

    }

    public void newuserMusic(){
        mp_n = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/k/k1.mp3"));
        mp_n.start();

    }
}
