package com.universitymlproject.cryptopredictor.dto.crypto;

import java.time.LocalDate;

public class CardanoDTOGet extends CryptoDTOGet{

    private Long id;
    private LocalDate date;
    private double close;
    private long volume;
    private double open;
    private double high;
    private double low;

    public CardanoDTOGet(Long id, LocalDate date, double close, long volume, double open, double high, double low) {
        super(id, date, close, volume, open, high, low);
    }
}
