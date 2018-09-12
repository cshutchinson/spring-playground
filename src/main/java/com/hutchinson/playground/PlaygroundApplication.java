package com.hutchinson.playground;

import com.hutchinson.playground.model.Employee;
import com.hutchinson.playground.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PlaygroundApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaygroundApplication.class, args);
	}


}
