package com.sberbank.xmlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XmlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlServiceApplication.class, args);
    }
}
