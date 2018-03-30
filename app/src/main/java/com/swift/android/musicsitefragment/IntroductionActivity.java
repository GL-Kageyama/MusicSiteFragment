package com.swift.android.musicsitefragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class IntroductionActivity extends AppCompatActivity {

    private MediaPlayer mp_i;

    private static String message;

    private static String message2;

    private static String message3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Intent intent = getIntent();
        message = intent.getStringExtra("MESSAGE1");
        message2 = intent.getStringExtra("MESSAGE2");
        message3 = intent.getStringExtra("MESSAGE3");

        System.out.println(message);
        System.out.println(message2);
        System.out.println(message3);

        introMusic();

        //illustがnullでない場合はallsearch()メソッドを行う
        //nullの場合はallsearch2()メソッドを行う
        if(message3 != null){
            allsearch(message);
        }else {
            allsearch2(message);
        }

    }

    public void introButton(View view){
        Intent intent = new Intent(this, Battle1Activity.class);
        mp_i.stop();
        startActivity(intent);
    }

    public void introMusic(){
        mp_i = MediaPlayer.create(this, Uri.parse("http://www.hmix.net/music/n/n78.mp3"));
        mp_i.start();
    }

    //新規ユーザ登録用
    public List<String> allsearch(String searchStr) {
        try {
            searchStr = URLEncoder.encode(searchStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //from newuser
        String urlAddString = "Newuser?name=" + message + "&password=" + message2 + "&illust=" + message3;

        String jsonString;

        jsonString = acsess(urlAddString);

        List<String> list = new ArrayList<String>();
        try {
            JSONArray loots = new JSONArray(jsonString);
            for (int i=0; i<loots.length(); i++) {
                JSONObject str = loots.getJSONObject(i);
                list.add(str.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    //ログイン用アクセス
    public List<String> allsearch2(String searchStr) {
        try {
            searchStr = URLEncoder.encode(searchStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //from login
        String urlAddString = "Login?name=" + message + "&password=" + message2;

        String jsonString;

        jsonString = acsess(urlAddString);

        List<String> list = new ArrayList<String>();
        try {
            JSONArray loots = new JSONArray(jsonString);
            for (int i=0; i<loots.length(); i++) {
                JSONObject str = loots.getJSONObject(i);
                list.add(str.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String acsess(String searchStr) {

        System.out.println(searchStr);

        StringBuffer jsonData = new StringBuffer();
        SwiftApplication.AcsessAsyncTask aat = new SwiftApplication.AcsessAsyncTask(jsonData);
        aat.execute(searchStr);

        System.out.println("aaa");

        int loopCnt = 10;
        loop:for (int i=0; i<loopCnt; i++) {
            if (jsonData.toString().length() != 0){
                break loop;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (jsonData.toString().length() == 0) {
            System.err.println("時間内に、データが取れなかった。アクセスできなかったよ！");
        }
        return jsonData.toString();
    }
}
