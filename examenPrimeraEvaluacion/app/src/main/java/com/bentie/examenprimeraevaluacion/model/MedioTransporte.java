package com.bentie.examenprimeraevaluacion.model;

import java.io.Serializable;

public class MedioTransporte implements Serializable {

    private String name;
    private String model;
    private String price;
    private int image;

    public MedioTransporte(String name, String model, String price, int image) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
