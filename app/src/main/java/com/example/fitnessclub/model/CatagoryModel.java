package com.example.fitnessclub.model;

public class CatagoryModel {
    private String productname;
    private String productdesc;
    private String productimg;
    private String type;
    private String catagoryId;

    public CatagoryModel(String productname, String productdesc, String productimg, String type, String catagoryId) {
        this.productname = productname;
        this.productdesc = productdesc;
        this.productimg = productimg;
        this.type = type;
        this.catagoryId = catagoryId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }
}
