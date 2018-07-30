package com.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan("com.apollo")
@EnableJms
@EnableAutoConfiguration()
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);

    }
}
