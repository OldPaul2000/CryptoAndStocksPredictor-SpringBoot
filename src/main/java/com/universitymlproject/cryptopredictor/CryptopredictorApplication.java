package com.universitymlproject.cryptopredictor;

import com.universitymlproject.cryptopredictor.service.cryptoservice.BitcoinService;
import com.universitymlproject.cryptopredictor.service.stocksservice.AmazonService;
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
	public CommandLineRunner commandLineRunner(AmazonService amazonService, BitcoinService bitcoinService){
		return runner -> {

		};
	}

}
