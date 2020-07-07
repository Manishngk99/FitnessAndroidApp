package com.example.fitnessclub.model;

public class UpdateUserModel {
    String fullname, address, phonenumber,weight, height;


    public UpdateUserModel(String fullname, String address, String phonenumber, String weight, String height) {
        this.fullname = fullname;
        this.address = address;
        this.phonenumber = phonenumber;
        this.weight = weight;
        this.height = height;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
