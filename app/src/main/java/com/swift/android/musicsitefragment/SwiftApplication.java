package com.swift.android.musicsitefragment;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;

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



public class SwiftApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static class AcsessAsyncTask extends AsyncTask<String, String, String> {

        private StringBuffer jsonResult;

        public AcsessAsyncTask(StringBuffer stringBuffer) {
            stringBuffer.reverse();
            jsonResult = stringBuffer;
        }

        @Override
        protected String doInBackground(String... string) {

            //MainActivityから送られてきた、name,password,illustに格納されているデータでユーザ登録　
            String urlStr = "http://192.168.1.200:8080/MSF_kageyama/nazonazo/"+ string[0];

            System.out.println(urlStr);

            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";
            try {
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                is = con.getInputStream();
                System.out.println("nazonazoにアクセス成功");
                System.out.println(is);

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                System.out.println(br);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    System.out.println(sb);
                    //sbによってプレイヤーが認証される
                }
                br.close();

                result = sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            jsonResult.append(result);
            return result;
        }
    }

}
