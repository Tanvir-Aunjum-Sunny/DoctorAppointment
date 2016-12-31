package com.example.hira.preproject3;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 8/20/2016.
 */
public class PatientList extends AppCompatActivity {
    Padapter padapter;
    ListView listView;
    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        padapter =new Padapter(this,R.layout.rowp);
        listView=(ListView)findViewById(R.id.patientlistview);
        listView.setAdapter(padapter);
        new GetData().execute();
        SearchView searchView= (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!=null){
                    padapter =new Padapter(PatientList.this,R.layout.rowp);
                    listView=(ListView)findViewById(R.id.patientlistview);
                    listView.setAdapter(padapter);
                    new SearchData().execute(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public class SearchData extends AsyncTask<String,Void,String> {
        JSONObject searchob;
        JSONArray searchar;
        String DATA, parsesearch, search_url;

        @Override
        protected void onPreExecute() {
            search_url = "http://10.0.3.2/androidproject/search.php";
        }

        @Override
        protected String doInBackground(String... strings) {
            String query = strings[0];
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String search = URLEncoder.encode("search", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8");
                bufferedWriter.write(search);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                httpURLConnection.connect();
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                while ((DATA = br.readLine()) != null) {
                    sb.append(DATA + "\n");
                }
                is.close();
                br.close();
                httpURLConnection.disconnect();
                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            parsesearch = s;
            try {
                searchob = new JSONObject(parsesearch);
                searchar = searchob.getJSONArray("Get_data");
                int count = 0;
                String sl, name, gender, type, did, adate;
                while (count < searchar.length()) {
                    JSONObject jo = searchar.getJSONObject(count);
                    sl = jo.getString("dbp_id");
                    name = jo.getString("dbp_name");
                    gender = jo.getString("dbp_gender");
                    type = jo.getString("dbp_type");
                    did = jo.getString("dbp_doctid");
                    adate = jo.getString("dbp_date");
                    Pmodel pmodel = new Pmodel(sl, name, gender, type, did, adate);
                    padapter.add(pmodel);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    public class GetData extends AsyncTask<String,Void,String>{
        JSONObject jsonObject;
        JSONArray jsonArray;
        String JSON,parsejson,plist_url;
        @Override
        protected void onPreExecute()
        {
          plist_url="http://10.0.3.2/androidproject/showpatient.php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL newurl=new URL(plist_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)newurl.openConnection();
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
        protected void onPostExecute(String result) {
            parsejson=result;
            try {
                jsonObject =new JSONObject(parsejson);
                jsonArray=jsonObject.getJSONArray("Get_data");
                int count=0;
                String sl,name,gender,type,did,adate;
                while (count<jsonArray.length()){
                    JSONObject jo=jsonArray.getJSONObject(count);
                    sl=jo.getString("dbp_id");
                    name=jo.getString("dbp_name");
                    gender=jo.getString("dbp_gender");
                    type=jo.getString("dbp_type");
                    did=jo.getString("dbp_doctorid");
                    adate=jo.getString("dbp_date");

                    Pmodel pmodel=new Pmodel(sl,name,gender,type,did,adate);
                    padapter.add(pmodel);
                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
