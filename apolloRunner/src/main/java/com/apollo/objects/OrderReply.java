package com.apollo.objects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trade")
public class OrderReply {

    private String buy;
    private String id;
    private String price;
    private String size;
    private String stock;
    private String whenAsDate;
    private String state;

    public OrderReply () {}

    public OrderReply(String buy, String id, String price, String size, String stock, String whenAsDate, String state) {
        this.buy = buy;
        this.id = id;
        this.price = price;
        this.size = size;
        this.stock = stock;
        this.whenAsDate = whenAsDate;
        this.state = state;
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
