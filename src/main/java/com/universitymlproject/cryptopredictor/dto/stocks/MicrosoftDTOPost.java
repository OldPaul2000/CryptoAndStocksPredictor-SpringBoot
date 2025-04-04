package com.universitymlproject.cryptopredictor.dto.stocks;

import java.time.LocalDate;

public class MicrosoftDTOPost extends StocksDTOPost{

    private LocalDate date;
    private double close;
    private long volume;
    private double open;
    private double high;
    private double low;

    public MicrosoftDTOPost(LocalDate date, double close, long volume, double open, double high, double low) {
        super(date, close, volume, open, high, low);
    }
}
