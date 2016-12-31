package com.example.hira.preproject3;
public class DoctorModel{
    private String id,name,degree,cat,time,day,visit,pic;

    public DoctorModel(String id, String name, String degree, String cat, String time, String day, String visit,String pic) {
        this.setId(id);
        this.setName(name);
        this.setDegree(degree);
        this.setCat(cat);
        this.setTime(time);
        this.setDay(day);
        this.setVisit(visit);
        this.setPic(pic);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}