package com.bentie.ejerciciorecopilatorio.model;

import java.io.Serializable;

public class Shipment implements Serializable {

    private int weight;
    private Zone zone;
    private boolean urgent;
    private boolean gift;
    private boolean card;

    public Shipment(int weight, Zone zone, boolean urgent, boolean gift, boolean card) {
        this.weight = weight;
        this.zone = zone;
        this.urgent = urgent;
        this.gift = gift;
        this.card = card;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public boolean hasCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }
}
