package com.br.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebSecurity
//@EnableSwagger2
public class LogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}


}
