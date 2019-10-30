package com.bentie.ejerciciorecopilatorio.model;

public class Zone {

    private int id;
    private int price;
    private String name;
    private int image;

    public Zone(int id, int price, String name, int image) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
