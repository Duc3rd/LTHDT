package com.example.btllthdt.model;

public class student {
    private int id;
    private String name;
    private String dob;
    private int addressProvider;
    private int provinder;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAddressProvider() {
        return addressProvider;
    }

    public void setAddressProvider(int addressProvider) {
        this.addressProvider = addressProvider;
    }

    public int getProvinder() {
        return provinder;
    }

    public void setProvinder(int provinder) {
        this.provinder = provinder;
    }
}
