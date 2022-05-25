package com.negra.projetapirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetApiRestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetApiRestApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ProjetApiRestApplication.class, args);

    }

}
