package com.example.fitnessclub.model;

public class HomeContentModel {
    private int id;
    private String productname;
    private String productdesc;
    private String productimg;
    private int userId;

    public HomeContentModel(int id, String productname, String productdesc, String productimg, int userId) {
        this.id = id;
        this.productname = productname;
        this.productdesc = productdesc;
        this.productimg = productimg;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductimg() {
        return productimg;
    }

    public void setProductimg(String productimg) {
        this.productimg = productimg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}