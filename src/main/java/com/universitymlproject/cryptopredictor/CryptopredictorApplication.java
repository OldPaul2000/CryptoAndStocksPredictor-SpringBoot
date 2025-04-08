package com.universitymlproject.cryptopredictor;

import com.universitymlproject.cryptopredictor.repository.crypto.GeneralCryptoRepository;
import com.universitymlproject.cryptopredictor.repository.stocks.GeneralStocksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptopredictorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptopredictorApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(GeneralCryptoRepository cryptoRepo, GeneralStocksRepository stocksRepo){
		return runner -> {


		};
	}

}
