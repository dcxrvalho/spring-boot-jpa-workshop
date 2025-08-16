package io.github.dcxrvalho.spring_boot_jpa_workshop.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.User;
import io.github.dcxrvalho.spring_boot_jpa_workshop.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(null, "Izabella", "izabella@gmail.com", "31951213409", "123456");
		User user2 = new User(null, "Nicolas", "nicolas@gmail.com", "71951213409", "123456");
		
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
	
	
}
