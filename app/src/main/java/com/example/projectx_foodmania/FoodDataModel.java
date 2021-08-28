package com.example.projectx_foodmania;

import java.io.Serializable;

public class FoodDataModel implements Serializable {

    private String name;
    private int price;
    private String description;
    private String link;

    public FoodDataModel() {
    }

    public FoodDataModel(String name, int price, String description, String link) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
