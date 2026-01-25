package com.vikas.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class BlogSocialApplication {

	public static void main(String[] args) {
		// CRITICAL: Set this BEFORE Spring starts or connects to DB
		System.setProperty("user.timezone", "Asia/Kolkata");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

		SpringApplication.run(BlogSocialApplication.class, args);
	}

}