package com.example.democlick;

import com.example.democlick.initilizer.TestDataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class DemoclickApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoclickApplication.class, args);
    }

    @Bean(initMethod = "init")
    @PostConstruct
    public TestDataInit initTestData() {
        return new TestDataInit();
    }
}
