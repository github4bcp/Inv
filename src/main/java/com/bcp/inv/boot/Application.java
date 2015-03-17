package com.bcp.inv.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
    	
        logger.info("Starting Server!");
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
   
    }
	
	
}
