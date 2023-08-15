package org.buysell;

import org.buysell.controller.product.ProductController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
