package org.buysell;

import org.buysell.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "org.buysell")
public class BuysellApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuysellApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductController productController){

        return runner ->{
            System.out.println("OK");
        };
    }
}
