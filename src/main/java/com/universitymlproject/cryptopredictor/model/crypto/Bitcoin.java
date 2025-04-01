package com.universitymlproject.cryptopredictor.model.crypto;

import jakarta.persistence.*;

@Entity
@Table(name = "bitcoin")
public class Bitcoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "low")
    private double low;

    @Column(name = "high")
    private double high;

    public Bitcoin() {}

    public Bitcoin(double low, double high) {
        this.low = low;
        this.high = high;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Bitcoin{" +
                "id=" + id +
                ", low=" + low +
                ", high=" + high +
                '}';
    }
}
