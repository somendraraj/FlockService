package com.flock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flock.common.Global;

@SpringBootApplication
public class FlockServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(FlockServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlockServiceApplication.class, args);

		/* To load all the config properties */
		Global.loadClass();

		log.info("**************Request Sending using IP & Port************");
	}
}
