package com.example.hira.preproject3;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Date;

/**
 * Created by user on 8/19/2016.
 */
public class Register extends AppCompatActivity {
    ArrayList<String> spinitem=new ArrayList<>();
    ArrayList<String> spinername=new ArrayList<>();
    Spinner sp;
    EditText name, age, phone, date;
    TextView spinname;
    String getname, getage, getgender, getpatient, getphone, getdate, getd_id, sex, p_type,temp,pres,other,symtom;
    CheckBox cb1, cb2, cb3, cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name=(EditText)findViewById(R.id.edName);
        age=(EditText)findViewById(R.id.edAge);
        phone=(EditText)findViewById(R.id.edPhone);
        date=(EditText)findViewById(R.id.edDate);
        cb1=(CheckBox)findViewById(R.id.Male);
        cb2=(CheckBox)findViewById(R.id.Female);
        cb3=(CheckBox)findViewById(R.id.New);
        cb4=(CheckBox)findViewById(R.id.Old);
        cb5=(CheckBox)findViewById(R.id.fever1);
        cb6=(CheckBox)findViewById(R.id.fever2);
        cb7=(CheckBox)findViewById(R.id.fever3);
        cb8=(CheckBox)findViewById(R.id.blood1);
        cb9=(CheckBox)findViewById(R.id.blood2);
        cb10=(CheckBox)findViewById(R.id.blood3);
        cb11=(CheckBox)findViewById(R.id.other1);
        cb12=(CheckBox)findViewById(R.id.other2);
        cb13=(CheckBox)findViewById(R.id.other3);
        sp= (Spinner) findViewById(R.id.spinner);
        spinname=(TextView)findViewById(R.id.spinname);
        new Getspin().execute();
    }


    private class Getspin extends AsyncTask<String,Void,String> {
        String spinurl,JSON,parsejson;
        JSONObject jo;
        JSONArray ja;
        @Override
        protected void onPreExecute() {
            spinurl="http://10.0.3.2/androidproject/spinner.php";
        }

        @Override
        protected String doInBackground(String... voids) {
            try {
                URL url=new URL(spinurl);
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
                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String res) {
            parsejson=res;
            try {
                jo =new JSONObject(parsejson);
                ja=jo.getJSONArray("get_data");
                int count=0;
                String id,name;
                SpinModel spinModel;
                while (count<ja.length()){
                    JSONObject spinjo=ja.getJSONObject(count);
                    id=spinjo.getString("id");
                    name=spinjo.getString("name");
                    spinModel=new SpinModel();
                    spinModel.setId(id);
                    spinModel.setName(name);
                    spinitem.add(spinModel.getId());
                    spinername.add(spinModel.getName());
                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayAdapter adapter=new ArrayAdapter(Register.this,R.layout.text,spinitem);
            sp.setAdapter(adapter);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinname.setText(spinername.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public void check(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.Male:
                if (checked)
                    sex = "Male";
                cb2.setChecked(false);
                break;
            case R.id.Female:
                if (checked)
                    sex = "Female";
                cb1.setChecked(false);
                break;


        }
    }

    public void pcheck(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.Old:
                if (checked)
                    p_type = "Old";
                cb3.setChecked(false);
                break;
            case R.id.New:
                if (checked)
                    p_type = "New";
                cb4.setChecked(false);
                break;


        }
    }


    public void tCheck(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.fever1:
                if (checked)
                    temp = "Below 100°F";
                cb6.setChecked(false);
                cb7.setChecked(false);
                break;
            case R.id.fever2:
                if (checked)
                   temp = "100°F-104°F";
                cb5.setChecked(false);
                cb7.setChecked(false);
                break;
            case R.id.fever3:
                if (checked)
                    temp = "above 104°F";
                cb5.setChecked(false);
                cb6.setChecked(false);
                break;


        }
    }

    public void bcheck(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.blood1:
                if (checked)
                    pres = "High";
                cb9.setChecked(false);
                cb10.setChecked(false);
                break;
            case R.id.blood2:
                if (checked)
                    pres = "Normal";
                cb8.setChecked(false);
                cb10.setChecked(false);
                break;
            case R.id.blood3:
                if (checked)
                    pres = "Low";
                cb8.setChecked(false);
                cb9.setChecked(false);
                break;


        }
    }
    public void ocheck(View view) {
        if(cb11.isChecked()&&cb12.isChecked()&&cb13.isChecked())
        {
            other="Headeche"+" , "+"Neckpain"+" , "+"Eyepain";
        }
        else if(cb11.isChecked()&&cb12.isChecked()){
            other="Headeche"+" , "+"Neckpain";
        }
        else if(cb12.isChecked()&&cb13.isChecked()){
            other="Neckpain"+" , "+"Eyepain";
        }
        else if(cb11.isChecked()&&cb13.isChecked()){
            other="Headeche"+" , "+"Eyepain";
        }
        else {
            other="";
        }
    }

    public void onAppoint(View view)
    {
        getname=name.getText().toString();
        getage=age.getText().toString();
        getgender=sex;
        getpatient=p_type;
        symtom="Temperature: "+temp+","+"Blood Pressure: "+pres+","+"Others: "+other;
        getphone= phone.getText().toString();
        getdate= date.getText().toString();
        getd_id= (String) sp.getSelectedItem();
        String method="Register";
        BackEndProcess backEndProcess=new BackEndProcess(this);
        backEndProcess.execute(method,getname,getage,getgender,getpatient,symtom,getphone,getdate,getd_id);
        finish();


    }
}
