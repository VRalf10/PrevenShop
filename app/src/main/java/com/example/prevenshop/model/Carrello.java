package com.example.prevenshop.model;

import android.util.Log;

import java.util.ArrayList;

public class Carrello {
    private String prod;
    private double totale = 0;
    public static final ArrayList<String> carrello= new ArrayList<String>();
    public Carrello(){

    }

    public Carrello(String prod) {
        this.prod= prod;
    }

    public void addElement(String Element){
        carrello.add(Element);
    }

    public ArrayList<String> getCarrello() {
        return carrello;
    }

    public void clearList(){
        carrello.clear();
    }

    public void setTotale(String price){
        double prezzo = Double.parseDouble(price);
        totale+=prezzo;
        Log.d("TOTALEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE", getTotale());
    }

    public String getTotale() {
        return Double.toString(totale);
    }
}
