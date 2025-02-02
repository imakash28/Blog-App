package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication	/*This annotation provides the configuration features or denotes that class as configuration class*/
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean	/*Using this annotation spring container automatically creates the object of this method and provide that object where we autowired this class*/
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	// How modelmappers work?
}
