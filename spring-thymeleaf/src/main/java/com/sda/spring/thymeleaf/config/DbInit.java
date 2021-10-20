package com.sda.spring.thymeleaf.config;

import com.sda.spring.thymeleaf.model.Product;
import com.sda.spring.thymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbInit {

    @Autowired
    private ProductService productService;

    @Bean
    public CommandLineRunner initialData() {
        return args -> {
            // load initial data in db
            Product product = new Product("tv", 5000.99);
            Product phone = new Product("phone", 4000.99);
            productService.save(product);
            productService.save(phone);
        };
    }
}
