package com.example.hira.preproject3;

/**
 * Created by user on 8/20/2016.
 */
public class Pmodel {
    private String id,name,gender,type,did,adate;

    public Pmodel(String id, String name, String gender, String type, String did,  String adate) {
     this.setId(id);
        this.setName(name);
        this.setGender(gender);
        this.setType(type);
        this.setDid(did);
        this.setAdate(adate);
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }
}
