package com.example.hira.preproject3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowDoctor extends AppCompatActivity{
    DoctorAdapter doctorAdapter;
    ListView listView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdoclist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        doctorAdapter = new DoctorAdapter(this, R.layout.docrow);
        listView = (ListView) findViewById(R.id.doclist);
        listView.setAdapter(doctorAdapter);
        new getDoctor().execute();
    }

    public class getDoctor extends AsyncTask<String,Void,String>{
        JSONObject jsonObject;
        JSONArray jsonArray;
        String JSON,parsejson,dlist_url;

        @Override
        protected void onPreExecute() {
            dlist_url="http://10.0.3.2/androidproject/showdoctor.php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url=new URL(dlist_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream is=httpURLConnection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                StringBuilder sb=new StringBuilder();
                while ((JSON=br.readLine())!=null){
                    sb.append(JSON+"\n");
                }
                is.close();
                br.close();
                httpURLConnection.disconnect();
                return sb.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            parsejson=s;
            try {
                jsonObject =new JSONObject(parsejson);
                jsonArray=jsonObject.getJSONArray("get_data");
                int count=0;
                String id,name,degree,cat,day,time,visit,pic;
                while (count<jsonArray.length()){
                    JSONObject jo=jsonArray.getJSONObject(count);
                    id=jo.getString("id");
                    name=jo.getString("name");
                    degree=jo.getString("deg");
                    cat=jo.getString("cat");
                    time=jo.getString("time");
                    day=jo.getString("day");
                    visit=jo.getString("visit");
                    pic=jo.getString("pic");
                    DoctorModel doctorModel=new DoctorModel(id,name,degree,cat,time,day,visit,pic);
                    doctorAdapter.add(doctorModel);
                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}