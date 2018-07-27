package com.apollo.com.apollo.entities;

import javax.persistence.*;
import java.io.Serializable;

public class BollingerBands extends Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id") private Integer id;

    @Column(name="timePeriod") private Integer timePeriod;
    @Column(name="stdDevs") private Integer stDevs;

    @JoinColumn(name="userId", referencedColumnName="id", nullable = false)
    private User user;

    @JoinColumn(name="strategyId", referencedColumnName="id", nullable = false)
    private Strategy strategy;
}
