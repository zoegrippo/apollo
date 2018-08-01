package com.apollo.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="strategies")
public class Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id") private Integer id;

    @Column(name="strategyname") private String strategyName;
    @Column(name="onoff") private boolean onoff;
    @Column(name="startingvol") private Integer startingVol;
    @Column(name="stock") private String stock; // stock ticker symbol
    @Column(name="exitprofitpercent") private Double exitProfitPercent;
    @Column(name="exitlosspercent") private Double exitLossPercent;
    @Column(name="stddevs") private Double stdDevs; // bollinger bands
    @Column(name="shorttime") private Integer shortTime; // two moving averages. short time also used for other two strategies
    @JoinColumn(name="userid", referencedColumnName="id", nullable = false)
    @ManyToOne
    private User user;

    public Strategy() {}

    public Strategy(String strategyName, boolean onoff, Integer startingVol, String stock, Double exitProfitPercent, Double exitLossPercent, Double stdDevs, Integer shortTime, User user) {
        this.strategyName = strategyName;
        this.onoff = onoff;
        this.startingVol = startingVol;
        this.stock = stock;
        this.exitProfitPercent = exitProfitPercent;
        this.exitLossPercent = exitLossPercent;
        this.stdDevs = stdDevs;
        this.shortTime = shortTime;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public boolean isOnoff() {
        return onoff;
    }

    public void setOnoff(boolean onoff) {
        this.onoff = onoff;
    }

    public Integer getStartingVol() {
        return startingVol;
    }

    public void setStartingVol(Integer startingVol) {
        this.startingVol = startingVol;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Double getExitProfitPercent() {
        return exitProfitPercent;
    }

    public void setExitProfitPercent(Double exitProfitPercent) {
        this.exitProfitPercent = exitProfitPercent;
    }

    public Double getExitLossPercent() {
        return exitLossPercent;
    }

    public void setExitLossPercent(Double exitLossPercent) {
        this.exitLossPercent = exitLossPercent;
    }

    public Double getStdDevs() {
        return stdDevs;
    }

    public void setStdDevs(Double stdDevs) {
        this.stdDevs = stdDevs;
    }

    public Integer getShortTime() {
        return shortTime;
    }

    public void setShortTime(Integer shortTime) {
        this.shortTime = shortTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}