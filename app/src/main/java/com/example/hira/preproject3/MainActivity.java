package com.example.hira.preproject3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRegister(View view)
    {
        Intent in= new Intent(this,Register.class);
        startActivity(in);
    }
    public void onPatient(View view)
    {
        Intent in= new Intent(this,PatientList.class);
        startActivity(in);
    }
    public void onShowDoc(View view)
    {
        Intent in= new Intent(this,ShowDoctor.class);
        startActivity(in);
    }
    public void onBasic(View view)
    {
        Intent in= new Intent(this,BasicPres.class);
        startActivity(in);
    }
    public void onHelp(View view)
    {
        Intent in= new Intent(this,Help.class);
        startActivity(in);
    }

    public void onExit(View view)
    {
        finish();
    }
}
