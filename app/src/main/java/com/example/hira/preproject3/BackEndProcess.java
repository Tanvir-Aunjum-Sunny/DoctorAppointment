package com.example.hira.preproject3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 8/19/2016.
 */
public class BackEndProcess extends AsyncTask <String,Void,String > {
    Context ctx;
    BackEndProcess(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String app_url="http://10.0.3.2/androidproject/register.php";
        String method=params[0];
        if(method.equals("Register")){
            String name=params[1];String age=params[2];String gender=params[3];String ptype=params[4];
            String symtom=params[5];String phn=params[6];String tarik=params[7];String did=params[8];
            try {
                URL url=new URL(app_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("panme","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("page","UTF-8") +"="+URLEncoder.encode(age,"UTF-8")+"&"+
                        URLEncoder.encode("psex","UTF-8") +"="+URLEncoder.encode(gender,"UTF-8")+"&"+
                        URLEncoder.encode("pcat","UTF-8") +"="+URLEncoder.encode(ptype,"UTF-8")+"&"+
                        URLEncoder.encode("symtom","UTF-8") +"="+URLEncoder.encode(symtom,"UTF-8")+"&"+
                        URLEncoder.encode("pphn","UTF-8") +"="+URLEncoder.encode(phn,"UTF-8")+"&"+
                        URLEncoder.encode("pdate","UTF-8") +"="+URLEncoder.encode(tarik,"UTF-8")+"&"+
                        URLEncoder.encode("d_id","UTF-8") +"="+URLEncoder.encode(did,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                return "Thank You! Appointment Confirmed!";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
