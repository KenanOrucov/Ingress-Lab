package com.example.Ingress_lab;

import com.example.Ingress_lab.service.CustomerService;
import com.example.Ingress_lab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
	private final UserService userService;
	private final CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(userService.fillUserData());
		System.out.println(userService.getUser());

		System.out.println(customerService.fillCustomerData());
		System.out.println(customerService.getCustomer());
	}
}

