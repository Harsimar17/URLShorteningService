package com.urlshorteningservice.URLShorteningService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UrlShorteningServiceApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UrlShorteningServiceApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(UrlShorteningServiceApplication.class, args);
	}

}
