package com.bcp.inv.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.bcp.inv.controller"})
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
    	
        logger.info("Starting Server!");
        SpringApplication.run(Application.class, args);
   
    }
	
	
}
