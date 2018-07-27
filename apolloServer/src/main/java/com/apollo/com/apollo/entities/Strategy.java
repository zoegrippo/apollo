package com.apollo.com.apollo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id") private Integer id;

    @Column(name="name") private String name;
    @Column(name="onoff") private boolean onoff;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnoff() {
        return onoff;
    }

    public void setOnoff(boolean onoff) {
        this.onoff = onoff;
    }
}
