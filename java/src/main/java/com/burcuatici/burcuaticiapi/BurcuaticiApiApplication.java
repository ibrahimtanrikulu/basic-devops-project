package com.burcuatici.burcuaticiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BurcuaticiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurcuaticiApiApplication.class, args);


    }
}
