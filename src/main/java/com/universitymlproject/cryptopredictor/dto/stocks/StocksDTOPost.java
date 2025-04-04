package com.universitymlproject.cryptopredictor.dto.stocks;

import java.time.LocalDate;

public class StocksDTOPost {

    private LocalDate date;
    private double close;
    private long volume;
    private double open;
    private double high;
    private double low;

    public StocksDTOPost(LocalDate date, double close, long volume, double open, double high, double low) {
        this.date = date;
        this.close = close;
        this.volume = volume;
        this.open = open;
        this.high = high;
        this.low = low;
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
