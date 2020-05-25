package com.example.prevenshop.model;

public class Data {

    private String image;
    private String title;
    private String description;
    private String id;
    private String price;

    public Data(){


    }

    public Data(String image, String title, String description, String id, String price) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.id = id;
        this.price = price;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
