package com.universitymlproject.cryptopredictor.model.stocks;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "netflix")
public class Netflix extends Stock{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "close")
    private Double close;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "open")
    private Double open;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Double low;

    public Netflix() {}
    public Netflix(LocalDate date, Double close, Long volume, Double open, Double high, Double low) {
        this.date = date;
        this.close = close;
        this.volume = volume;
        this.open = open;
        this.high = high;
        this.low = low;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public Double getClose() {
        return close;
    }

    @Override
    public void setClose(Double close) {
        this.close = close;
    }

    @Override
    public Long getVolume() {
        return volume;
    }

    @Override
    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public Double getOpen() {
        return open;
    }

    @Override
    public void setOpen(Double open) {
        this.open = open;
    }

    @Override
    public Double getHigh() {
        return high;
    }

    @Override
    public void setHigh(Double high) {
        this.high = high;
    }

    @Override
    public Double getLow() {
        return low;
    }

    @Override
    public void setLow(Double low) {
        this.low = low;
    }
}
