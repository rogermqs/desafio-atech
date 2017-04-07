package br.com.atech;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class DesafioAtechApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioAtechApplication.class, args);
	}
	
}
