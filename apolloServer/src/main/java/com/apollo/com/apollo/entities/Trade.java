package com.apollo.com.apollo.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="trades")

@NamedQueries(
        {
                @NamedQuery(name="trades.getById",
                        query="select trade from Trade as trade where trade.id = :id",
                        hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        })

public class Trade implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id") private Integer id;
    @Column(name="buy") private boolean buy;
    @Column(name="price") private double price;
    @Column(name="size") private Integer size;
    @Column(name="stock") private String stock;
    @Column(name="tradeDate") private Timestamp tradeDate;
    @Column(name="state") private String state;

    @JoinColumn (name="strategyId", referencedColumnName="id", nullable = false)
    @ManyToOne
    private Strategy strategy;

    @JoinColumn (name="userId", referencedColumnName="id", nullable = false)
    private User user;

    public Trade() {}

    public Trade(boolean buy, double price, Integer size, String stock, Timestamp tradeDate, String state, Strategy strategy, User user) {
        this.buy = buy;
        this.price = price;
        this.size = size;
        this.stock = stock;
        this.tradeDate = tradeDate;
        this.state = state;
        this.strategy = strategy;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
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

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
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
