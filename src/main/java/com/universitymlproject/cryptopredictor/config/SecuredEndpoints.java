package com.universitymlproject.cryptopredictor.config;

public class SecuredEndpoints {

    public final static String[] CRYPTO_ENDPOINTS = {
            "/api/v1/cryptos/bitcoin/**",
            "/api/v1/cryptos/cardano/**",
            "/api/v1/cryptos/ethereum/**",
            "/api/v1/cryptos/litecoin/**",
            "/api/v1/cryptos/ripple/**"
    };
    
    public final static String[] STOCKS_ENDPOINTS = {
            "/api/v1/stocks/amazon/**",
            "/api/v1/stocks/amd/**",
            "/api/v1/stocks/apple/**",
            "/api/v1/stocks/microsoft/**",
            "/api/v1/stocks/netflix/**",
            "/api/v1/stocks/starbucks/**",
            "/api/v1/stocks/tesla/**"
    };

    public final static String UPDATE_ENDPOINTS = "/api/v1/data/update/**";
    public final static String CSV_FILE_ENDPOINTS = "/api/v1/csv/data/**";
    public final static String EXCEL_FILE_ENDPOINTS = "/api/v1/excel/data/**";

}
