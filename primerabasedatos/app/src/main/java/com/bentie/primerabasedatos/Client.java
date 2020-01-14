package com.bentie.primerabasedatos;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Client implements Serializable {

    private String id;
    private String name;
    private String phone;
    private int image;

    public Client() {
    }

    public Client(String id, String name, String phone, int img) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s: %s %s %d", id, name, phone, image);
    }
}
