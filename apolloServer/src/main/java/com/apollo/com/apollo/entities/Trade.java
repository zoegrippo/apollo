package com.apollo.com.apollo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="trades")

public class Trade implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id") private int id;

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
}
