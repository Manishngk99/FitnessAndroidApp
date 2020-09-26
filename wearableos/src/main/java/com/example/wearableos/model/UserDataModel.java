package com.example.wearableos.model;

public class UserDataModel {
    private String username;
    private String profileimage;
    private String phonenumber;
    private String weight;
    private String height;

    public UserDataModel(String username, String profileimage, String phonenumber, String weight, String height) {
        this.username = username;
        this.profileimage = profileimage;
        this.phonenumber = phonenumber;
        this.weight = weight;
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
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
