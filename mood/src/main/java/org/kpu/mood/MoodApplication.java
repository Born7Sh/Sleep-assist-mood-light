package org.kpu.mood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan({"org.kpu.mood.controller"})
public class MoodApplication extends SpringBootServletInitializer{
	//SpringBootServletInitializer는 Spring Boot를 tomcat에 배포하기위해 사용.
	
	public static void main(String[] args) {
		SpringApplication.run(MoodApplication.class, args);
	}

}
