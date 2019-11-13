package com.bentie.examenprimeraevaluacion.model;

import java.io.Serializable;

public class Rent implements Serializable {

    private MedioTransporte transport;
    private int days;
    private boolean hasHelmet;
    private boolean hasGps;
    private boolean hasExtras;
    private boolean hasInsurance;

    public MedioTransporte getTransport() {
        return transport;
    }

    public void setTransport(MedioTransporte transport) {
        this.transport = transport;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public boolean isHasGps() {
        return hasGps;
    }

    public void setHasGps(boolean hasGps) {
        this.hasGps = hasGps;
    }

    public boolean isHasExtras() {
        return hasExtras;
    }

    public void setHasExtras(boolean hasExtras) {
        this.hasExtras = hasExtras;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }
}
