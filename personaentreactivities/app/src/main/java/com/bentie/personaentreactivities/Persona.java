package com.bentie.personaentreactivities;

import java.io.Serializable;

public class Persona implements Serializable {

    private String name;
    private String surname;
    private int age;
    private int picture;

    public Persona(String name, String surname, int age, int picture) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "Nombre='" + name + '\'' +
                ", Apellido='" + surname + '\'' +
                ", Edad=" + age +
                '}';
    }
}
