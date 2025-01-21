package DistribuidorDeGastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"controller", "service"})
@SpringBootApplication
public class DistribuidorDeGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistribuidorDeGastosApplication.class, args);
	}

}
