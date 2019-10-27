/*
 * Copyright (c) 2018 Benito Barreiro <BenitoBarreiro89@gmail.com>
 */

package com.benitob.listadobleconobjetos.model;

import java.util.List;

public class Author {

    private int image;
    private String name;
    private List<Book> books;

    public Author(int image, String name, List<Book> books) {
        this.image = image;
        this.name = name;
        this.books = books;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int booksWritten(){ return books.size(); }
}
