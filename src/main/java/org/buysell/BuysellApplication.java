package org.buysell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "org.buysell")
public class BuysellApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuysellApplication.class, args);
    }

}
