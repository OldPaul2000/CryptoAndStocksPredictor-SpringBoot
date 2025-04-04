package com.universitymlproject.cryptopredictor.dto.stocks;

import java.time.LocalDate;

public class StocksDTOGet {

    private Long id;
    private LocalDate date;
    private double close;
    private long volume;
    private double open;
    private double high;
    private double low;

    public StocksDTOGet(Long id, LocalDate date, double close, long volume, double open, double high, double low) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public double getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }
}
