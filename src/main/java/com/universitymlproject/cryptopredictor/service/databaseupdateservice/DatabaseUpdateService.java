package com.universitymlproject.cryptopredictor.service.databaseupdateservice;

import com.universitymlproject.cryptopredictor.model.crypto.*;
import com.universitymlproject.cryptopredictor.model.datafiles.CsvCurrency;
import com.universitymlproject.cryptopredictor.model.stocks.*;
import com.universitymlproject.cryptopredictor.repository.crypto.GeneralCryptoRepository;
import com.universitymlproject.cryptopredictor.repository.stocks.GeneralStocksRepository;
import com.universitymlproject.cryptopredictor.service.filesservice.CsvFileService;
import com.universitymlproject.cryptopredictor.service.filesservice.ExcelService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseUpdateService {

    private GeneralCryptoRepository cryptoRepository;
    private GeneralStocksRepository stocksRepository;
    private CsvFileService csvFileService;
    private ExcelService excelService;
    private CurrencyCaster currencyCaster;

    public DatabaseUpdateService(GeneralCryptoRepository cryptoRepository,
                                 GeneralStocksRepository stocksRepository,
                                 CsvFileService csvFileService,
                                 ExcelService excelService,
                                 CurrencyCaster currencyCaster) {
        this.cryptoRepository = cryptoRepository;
        this.stocksRepository = stocksRepository;
        this.csvFileService = csvFileService;
        this.excelService = excelService;
        this.currencyCaster = currencyCaster;
    }

    private List<Crypto> newCryptoData;
    private List<Stock> newStockData;
    private Class<? extends Crypto> cryptoToUpdate;
    private Class<? extends Stock> stockToUpdate;

    public void updateDatabase(){
        if(cryptoToUpdate != null){
            newCryptoData.forEach(element -> {
                Crypto crypto = currencyCaster.castCrypto(element, cryptoToUpdate);
                cryptoRepository.persistEntity(crypto);
            });
            newCryptoData = new ArrayList<>();
            cryptoToUpdate = null;
        }
        else if(stockToUpdate != null){
            newStockData.forEach(element -> {
                Stock stock = currencyCaster.castStock(element, stockToUpdate);
                System.out.println(stock);
//                stocksRepository.persistEntity(stock);
            });
            newStockData = new ArrayList<>();
            stockToUpdate = null;
        }
    }

    public void resetData(){
        newCryptoData.clear();
        newStockData.clear();
        cryptoToUpdate = null;
        stockToUpdate = null;
    }

    public List<Stock> getNewStockData(String stockClass, String fileName){
        newCryptoData = new ArrayList<>();
        newStockData = new ArrayList<>();
        cryptoToUpdate = null;

        Stock currency = stocksRepository.findLastRecord(stocksClasses.get(stockClass));
        LocalDate lastRecordDatabaseDate = currency.getDate();
        csvFileService.parseCsvData(fileName, lastRecordDatabaseDate);
        csvFileService.getData().forEach(record -> {
            newStockData.add(mapFromCsvToStock(record));
        });
        stockToUpdate = stocksClasses.get(stockClass);

        return newStockData;
    }

    private final Map<String, Class<? extends Stock>> stocksClasses = Map.ofEntries(
            Map.entry("amazon", Amazon.class),
            Map.entry("amd", Amd.class),
            Map.entry("apple", Apple.class),
            Map.entry("microsoft", Microsoft.class),
            Map.entry("netflix", Netflix.class),
            Map.entry("starbucks", Starbucks.class),
            Map.entry("tesla", Tesla.class)
    );

    private Stock mapFromCsvToStock(CsvCurrency csvCurrency){
        Stock stock = new Stock(
                csvCurrency.getDate(),
                csvCurrency.getClose(),
                csvCurrency.getVolume(),
                csvCurrency.getOpen(),
                csvCurrency.getHigh(),
                csvCurrency.getLow()
        );
        stock.setId(csvCurrency.getId());
        return stock;
    }

    public List<Crypto> getNewCryptoData(String cryptoClass, String fileName){
        newCryptoData = new ArrayList<>();
        newStockData = new ArrayList<>();
        stockToUpdate = null;

        Crypto currency = cryptoRepository.findLastRecord(cryptoClasses.get(cryptoClass));
        LocalDate lastRecordDatabaseDate = currency.getDate();
        csvFileService.parseCsvData(fileName, lastRecordDatabaseDate);
        csvFileService.getData().forEach(record -> {
            newCryptoData.add(mapFromCsvToCrypto(record));
        });
        cryptoToUpdate = cryptoClasses.get(cryptoClass);

        return newCryptoData;
    }

    private final Map<String, Class<? extends Crypto>> cryptoClasses = Map.ofEntries(
            Map.entry("bitcoin", Bitcoin.class),
            Map.entry("cardano", Cardano.class),
            Map.entry("ethereum", Ethereum.class),
            Map.entry("litecoin", Litecoin.class),
            Map.entry("ripple", Ripple.class)
    );

    private Crypto mapFromCsvToCrypto(CsvCurrency csvCurrency){
        Crypto crypto = new Crypto(
                csvCurrency.getDate(),
                csvCurrency.getClose(),
                csvCurrency.getVolume(),
                csvCurrency.getOpen(),
                csvCurrency.getHigh(),
                csvCurrency.getLow()
        );
        crypto.setId(csvCurrency.getId());
        return crypto;
    }

}
