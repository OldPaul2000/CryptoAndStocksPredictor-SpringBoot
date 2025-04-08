package com.universitymlproject.cryptopredictor.service.databaseupdateservice;

import com.universitymlproject.cryptopredictor.model.crypto.Crypto;
import com.universitymlproject.cryptopredictor.model.stocks.Stock;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.time.LocalDate;

@Service
public class CurrencyCaster {

    public Crypto castCrypto(Crypto currency, Class<? extends Crypto> cryptoToUpdate){
        try{
            Constructor<? extends Crypto> constructor = cryptoToUpdate.getDeclaredConstructor(
                    LocalDate.class,
                    Double.class,
                    Long.class,
                    Double.class,
                    Double.class,
                    Double.class
            );
            return constructor.newInstance(
                    currency.getDate(),
                    currency.getClose(),
                    currency.getVolume(),
                    currency.getOpen(),
                    currency.getHigh(),
                    currency.getLow()
            );
        }
        catch (Exception e){
            return null;
        }
    }

    public Stock castStock(Stock currency, Class<? extends Stock> stockToUpdate){
        try{
            Constructor<? extends Stock> constructor = stockToUpdate.getDeclaredConstructor(
                    LocalDate.class,
                    Double.class,
                    Long.class,
                    Double.class,
                    Double.class,
                    Double.class
            );
            return constructor.newInstance(
                    currency.getDate(),
                    currency.getClose(),
                    currency.getVolume(),
                    currency.getOpen(),
                    currency.getHigh(),
                    currency.getLow()
            );
        }
        catch (Exception e){
            return null;
        }
    }

}
