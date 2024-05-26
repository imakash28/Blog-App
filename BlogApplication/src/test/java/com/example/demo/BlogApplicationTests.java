package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repo.UserRepository;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
	}
	/*Here, we have implemented a test method to check which class and package we have autowired in UserController class */
	@Test
	public void repoTest() {
		String className=this.userRepository.getClass().getName();
		String packageName=this.userRepository.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}

}
