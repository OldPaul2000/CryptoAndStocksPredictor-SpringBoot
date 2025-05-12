package com.universitymlproject.cryptopredictor;

import com.universitymlproject.cryptopredictor.service.filesservice.CsvFileService;
import com.universitymlproject.cryptopredictor.service.filesservice.ExcelService;
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
	public CommandLineRunner commandLineRunner(CsvFileService csv, ExcelService exl){
		return runner -> {

		};
	}

}
