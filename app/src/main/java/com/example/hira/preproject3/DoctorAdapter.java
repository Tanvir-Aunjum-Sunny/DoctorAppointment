package com.example.hira.preproject3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends ArrayAdapter{
    List list=new ArrayList();
    ImageLoader imageLoader=AppController.getInstance().getImageLoader();

    public DoctorAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
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
        DoctorHolder doctorHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.docrow,parent,false);
            doctorHolder=new DoctorHolder();
            doctorHolder.id=(TextView)row.findViewById(R.id.id);
            doctorHolder.name=(TextView)row.findViewById(R.id.name);
            doctorHolder.degree=(TextView)row.findViewById(R.id.deg);
            doctorHolder.cat=(TextView)row.findViewById(R.id.cat);
            doctorHolder.time=(TextView)row.findViewById(R.id.time);
            doctorHolder.day=(TextView)row.findViewById(R.id.day);
            doctorHolder.visit=(TextView)row.findViewById(R.id.visit);
            doctorHolder.imageView=(NetworkImageView)row.findViewById(R.id.thumbnail);
            row.setTag(doctorHolder);
        }
        else {doctorHolder= (DoctorHolder) row.getTag();}
        DoctorModel doctorModel= (DoctorModel) this.getItem(position);
        doctorHolder.id.setText(doctorModel.getId());
        doctorHolder.name.setText(doctorModel.getName());
        doctorHolder.degree.setText(doctorModel.getDegree());
        doctorHolder.cat.setText(doctorModel.getCat());
        doctorHolder.time.setText(doctorModel.getTime());
        doctorHolder.day.setText(doctorModel.getDay());
        doctorHolder.visit.setText(doctorModel.getVisit());
        doctorHolder.imageView.setImageUrl(doctorModel.getPic(),imageLoader);
        return row;
    }
    static class DoctorHolder{
        TextView id,name,degree,cat,day,time,visit;
        NetworkImageView imageView;
    }
}