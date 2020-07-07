package com.example.fitnessclub.model;

public class Product {
    private String productname;
    private String productdesc;
    private String productimg;


    public Product(String productname, String productdesc, String productimg) {
        this.productname = productname;
        this.productdesc = productdesc;
        this.productimg = productimg;
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
}
