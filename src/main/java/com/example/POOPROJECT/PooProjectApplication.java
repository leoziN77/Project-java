package com.example.POOPROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.service.LibraryService;

@SpringBootApplication
@ComponentScan("com.example")
public class PooProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PooProjectApplication.class, args);
        PooProjectApplication app = context.getBean(PooProjectApplication.class);
        app.run();
    }

	@Bean
	public LibraryService libraryService() {
		return new LibraryService();
	}
	
    public void run() {
    }
}
