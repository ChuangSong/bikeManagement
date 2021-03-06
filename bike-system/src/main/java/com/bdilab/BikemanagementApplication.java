package com.bdilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
public class BikemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikemanagementApplication.class, args);
    }

}
