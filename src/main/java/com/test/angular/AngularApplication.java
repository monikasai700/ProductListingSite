package com.test.angular;

import com.test.angular.models.TestUser;
import com.test.angular.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class AngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularApplication.class, args);
	}
    @Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			Stream.of("John","julie","Jamie","Ana","Adya").forEach( name->{
					TestUser user = new TestUser(name,name.toLowerCase()+"@domain.com");
					userRepository.save(user);
					}
			);
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
