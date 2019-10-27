/*
 * Copyright (c) 2018 Benito Barreiro <BenitoBarreiro89@gmail.com>
 */

package com.benitob.listadobleconobjetos.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String desc;
    private int image;

    public Book(){}

    public Book( String title, String desc, int image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image=" + image +
                '}';
    }
}
