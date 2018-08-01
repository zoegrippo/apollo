package com.apollo.objects;

import com.apollo.entities.Trade;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.sql.Timestamp;

public class Order {

    private String buy;
    private String id;
    private String price;
    private String size;
    private String stock;
    private String whenAsDate;
    private String state;


    public Order (Trade t ) {
        this.buy = t.getBuy() ? "true" : "false";
        this.id = t.getId().toString();
        this.price = Double.toString(t.getPrice());
        this.size = Integer.toString(t.getSize());
        this.stock = t.getStock();
        this.whenAsDate = t.getTradeDate().toString();
    }

    public Order (String s){
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(s));

            Document doc = db.parse(is);
            Element root = doc.getDocumentElement();

            this.buy = root.getElementsByTagName("buy").item(0).getTextContent();
            this.id = root.getElementsByTagName("id").item(0).getTextContent();
            this.price = root.getElementsByTagName("price").item(0).getTextContent();
            this.state = root.getElementsByTagName("result").item(0).getTextContent().toLowerCase();
            this.size = root.getElementsByTagName("size").item(0).getTextContent();
            this.stock = root.getElementsByTagName("stock").item(0).getTextContent();
            this.whenAsDate = root.getElementsByTagName("whenAsDate").item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String s = "<trade>";
        s += "<buy>"+this.buy+"</buy> ";
        s += "<id>"+this.id+"</id>";
        s += "<price>"+this.price+"</price>";
        s += "<size>"+this.size+"</size>";
        s += "<stock>"+this.stock+"</stock>";
        s += "<whenAsDate>"+this.whenAsDate+"</whenAsDate>";
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