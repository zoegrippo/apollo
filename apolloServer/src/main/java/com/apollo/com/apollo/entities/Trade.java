package com.apollo.com.apollo.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="trades")

public class Trade implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id") private Integer id;

    @Column(name="buysell") private boolean buysell;
    @Column(name="price") private double price;
    @Column(name="size") private Integer size;
    @Column(name="stock") private String stock;
    @Column(name="tradeDate") private Date tradeDate;
    @Column(name="state") private String state;

    @JoinColumn (name="strategyId", referencedColumnName="id", nullable = false)
    @ManyToOne
    private Strategy strategy;

    @JoinColumn (name="userId", referencedColumnName="id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isBuysell() {
        return buysell;
    }

    public void setBuysell(boolean buysell) {
        this.buysell = buysell;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
