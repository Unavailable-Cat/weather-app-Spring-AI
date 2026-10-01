package com.springAI.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToolsApplication {

	public static void main(String[] args) {
		System.out.println(
				System.getenv("GOOGLE_GENAI_API_KEY") != null
						? "GOOGLE KEY FOUND"
						: "GOOGLE KEY NOT FOUND"
		);
		SpringApplication.run(ToolsApplication.class, args);
	}

}
