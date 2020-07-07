package com.example.fitnessclub.model;

public class UserModel {
    private String fullname;
    private String username;
    private String password;
    private String address;
    private String phonenumber;
    private String profileimage;
    private String weight;
    private String height;

    public UserModel(String fullname, String username, String password, String address, String phonenumber, String profileimage, String weight, String height) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phonenumber = phonenumber;
        this.profileimage = profileimage;
        this.weight = weight;
        this.height = height;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
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
