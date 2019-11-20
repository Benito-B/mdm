package com.bentie.figurasaleatorias.model;

public class DrawShapesCounter {

    private int black = 0;
    private int blue = 0;
    private int green = 0;
    private int red = 0;

    public DrawShapesCounter() {
    }

    public void increaseBlack(){ black++; }

    public void increaseBlue(){
        blue++;
    }

    public void increaseGreen(){
        green++;
    }

    public void increaseRed(){
        red++;
    }

    public void decreaseBlack(){ black--; }

    public void decreaseBlue(){ blue--; }

    public void decreaseGreen(){ green--; }

    public void decreaseRed(){ red--; }

    public int getBlack() {
        return black;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public int getRed() {
        return red;
    }
}
