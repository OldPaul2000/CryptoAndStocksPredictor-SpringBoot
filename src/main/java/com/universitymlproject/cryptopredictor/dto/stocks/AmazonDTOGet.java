package com.universitymlproject.cryptopredictor.dto.stocks;

import java.time.LocalDate;

public class AmazonDTOGet extends StocksDTOGet{

        private Long id;
        private LocalDate date;
        private double close;
        private long volume;
        private double open;
        private double high;
        private double low;

    public AmazonDTOGet(Long id, LocalDate date, double close, long volume, double open, double high, double low) {
        super(id, date, close, volume, open, high, low);
    }
}
