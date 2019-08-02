package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MuzixServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuzixServiceApplication.class, args);
    }

}