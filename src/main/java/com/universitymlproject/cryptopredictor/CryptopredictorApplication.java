package com.universitymlproject.cryptopredictor;

import com.universitymlproject.cryptopredictor.repository.UserRepository;
import com.universitymlproject.cryptopredictor.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptopredictorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptopredictorApplication.class, args);
	}

	/**TODO
	 * Secure api endpoints properly
	 */

	@Bean
	public CommandLineRunner commandLineRunner(UserService userService, UserRepository userRepository){
		return runner -> {
			System.out.println(userRepository.findUserByUsernameWithRoles("John"));
		};
	}

}
