package com.apollo.objects;

import com.apollo.entities.Trade;

public class Order {

    private String buy;
    private String id;
    private String price;
    private String size;
    private String stock;
    private String whenAsDate;
    private String state;


    public Order(Trade t) {
        this.buy = t.getBuy() ? "true" : "false";
        this.id = t.getId().toString();
        this.price = Double.toString(t.getPrice());
        this.size = Integer.toString(t.getSize());
        this.stock = t.getStock();
        this.whenAsDate = t.getTradeDate().toString();
    }


    public String toString() {
        String s = "<trade>";
        s += "<buy>" + this.buy + "</buy> ";
        s += "<id>" + this.id + "</id>";
        s += "<price>" + this.price + "</price>";
        s += "<size>" + this.size + "</size>";
        s += "<stock>" + this.stock + "</stock>";
        s += "<whenAsDate>" + this.whenAsDate + "</whenAsDate>";
        s += "</trade>";
        return s;

    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getWhenAsDate() {
        return whenAsDate;
    }

    public void setWhenAsDate(String whenAsDate) {
        this.whenAsDate = whenAsDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}