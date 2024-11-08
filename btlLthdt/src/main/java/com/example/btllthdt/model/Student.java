package com.example.btllthdt.model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private Date dob;
    private int addressProvince;
    private int province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(int addressProvince) {
        this.addressProvince = addressProvince;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }
}