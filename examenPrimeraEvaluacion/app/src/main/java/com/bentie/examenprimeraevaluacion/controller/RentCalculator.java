package com.bentie.examenprimeraevaluacion.controller;

import com.bentie.examenprimeraevaluacion.model.Rent;

public class RentCalculator {

    private static final float INSURANCE_COST = 1.2f;

    private Rent rent;

    public RentCalculator(Rent rent){
        this.rent = rent;
    }

    public int calculatePrice(){
        int price = 0;
        //Primero sumo los costes fijos de los extras
        price += getExtrasCost();
        //Luego sumo el coste del aquiler por día
        int costPerDay = Integer.parseInt(rent.getTransport().getPrice());
        int days = rent.getDays();
        price += (costPerDay * days);
        //Por último si tiene seguro le sumo un 20%
        if(rent.isHasInsurance())
            price *= INSURANCE_COST;
        return price;
    }

    public String getInsurance(){
        if(rent.isHasInsurance())
            return "Con seguro";
        return "Sin seguro";
    }

    public int getExtrasCost(){
        int p = 0;
        if(rent.isHasHelmet())
            p += 50;
        if(rent.isHasGps())
            p += 50;
        if(rent.isHasExtras())
            p += 50;
        return p;
    }

}
