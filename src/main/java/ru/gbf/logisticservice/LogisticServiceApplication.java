package ru.gbf.logisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"ru.gbf.logisticservice.config","ru.gbf.logisticservice.controller",
        "ru.gbf.logisticservice.dao","ru.gbf.logisticservice.service","ru.gbf.logisticservice.mapper",
        "com.gbf.auth.filter.integration"})
public class LogisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticServiceApplication.class, args);
    }
}