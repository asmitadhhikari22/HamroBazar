package com.asmit.hamrobazar;

public class Trending_ads {

    public Trending_ads (String name, String price, int imageId, String type) {
        this.name = name;
        this.price = price;
        this.imageId = imageId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;
    private  String price;
    private  int imageId;
    private  String type;
}


