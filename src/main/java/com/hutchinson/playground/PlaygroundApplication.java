package com.hutchinson.playground;

import com.hutchinson.playground.model.Employee;
import com.hutchinson.playground.repo.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class PlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaygroundApplication.class, args);
	}

	@Bean
	@Profile("default")
	public CommandLineRunner seedData(EmployeeRepository employeeRepository) {
		return (args) -> {
			employeeRepository.deleteAll();
			Employee employee = new Employee();
			employee.setName("Employee");
			employee.setSalary(24);
			employee.setUsername("employee");
			employee.setPassword("my-employee-password");
			employee.setRole("EMPLOYEE");
			employeeRepository.save(employee);

			Employee boss = new Employee();
			boss.setName("Bossy Boss");
			boss.setSalary(24);
			boss.setUsername("boss");
			boss.setPassword("my-boss-password");
			boss.setRole("MANAGER");
			employeeRepository.save(boss);
		};
	}
}
