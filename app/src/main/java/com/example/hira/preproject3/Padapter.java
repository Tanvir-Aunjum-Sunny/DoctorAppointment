package com.example.hira.preproject3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/20/2016.
 */
public class Padapter extends ArrayAdapter {
    List list=new ArrayList();

    public Padapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Pmodel object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        PatientHolder patientHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.rowp,parent,false);
            patientHolder=new PatientHolder();
            patientHolder.listname=(TextView)row.findViewById(R.id.plistname);
            patientHolder.listid=(TextView)row.findViewById(R.id.plistid);
            patientHolder.listgender=(TextView)row.findViewById(R.id.plistgender);
            patientHolder.listtype=(TextView)row.findViewById(R.id.plisttype);
            patientHolder.listdid=(TextView)row.findViewById(R.id.plistdid);
            patientHolder.listdate=(TextView)row.findViewById(R.id.plistdate);
            row.setTag(patientHolder);
        }
        else {
            patientHolder=(PatientHolder)row.getTag();
        }
        Pmodel patientModel=(Pmodel)this.getItem(position);
        patientHolder.listid.setText(patientModel.getId());
        patientHolder.listname.setText(patientModel.getName());
        patientHolder.listgender.setText(patientModel.getGender());
        patientHolder.listtype.setText(patientModel.getType());
        patientHolder.listdid.setText(patientModel.getDid());
        patientHolder.listdate.setText(patientModel.getAdate());
        return row;
    }
    static class PatientHolder{
        TextView listid,listname,listgender,listtype,listdid,listdate;
    }
}
