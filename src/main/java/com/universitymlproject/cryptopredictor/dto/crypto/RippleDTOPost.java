package com.universitymlproject.cryptopredictor.dto.crypto;

import java.time.LocalDate;

public class RippleDTOPost extends CryptoDTOPost {

    private LocalDate date;
    private double close;
    private long volume;
    private double open;
    private double high;
    private double low;

    public RippleDTOPost(LocalDate date, double close, long volume, double open, double high, double low) {
        super(date, close, volume, open, high, low);
    }
}
