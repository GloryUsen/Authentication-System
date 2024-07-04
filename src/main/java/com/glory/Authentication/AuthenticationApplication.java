package com.glory.Authentication;

import com.glory.Authentication.model.AppUser;
import com.glory.Authentication.model.Role;
import com.glory.Authentication.reposiotry.RoleRepository;
import com.glory.Authentication.reposiotry.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role(1,"ADMIN"));
			roleRepository.save(new Role(2,"USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			AppUser admin = new AppUser();
			admin.setEmail("ADMIN");
			admin.setPhoneNumber("password");

			userRepository.save(admin);
		};
	}

}
