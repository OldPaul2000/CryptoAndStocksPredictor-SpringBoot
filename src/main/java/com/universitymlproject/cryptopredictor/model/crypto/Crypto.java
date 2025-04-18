package com.universitymlproject.cryptopredictor.model.crypto;

import java.time.LocalDate;

public class Crypto {

    private Long id;
    private LocalDate date;
    private Double close;
    private Long volume;
    private Double open;
    private Double high;
    private Double low;

    public Crypto() {}

    public Crypto(LocalDate date, Double close, Long volume, Double open, Double high, Double low) {
        this.date = date;
        this.close = close;
        this.volume = volume;
        this.open = open;
        this.high = high;
        this.low = low;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id=" + id +
                ", date=" + date +
                ", close=" + close +
                ", volume=" + volume +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}
