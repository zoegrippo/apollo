package com.apollo.objects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String symbol;
    private double price;
    private int volume;

    public Quote (String[] params) {
        this.symbol = params[0];
        this.price = Double.parseDouble(params[1]);
        this.volume =  Integer.parseInt(params[2]);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String toString() {
        return "Symbol: " + this.symbol + ", Price: " + this.price + ", Volume: " + this.volume;
    }
}
