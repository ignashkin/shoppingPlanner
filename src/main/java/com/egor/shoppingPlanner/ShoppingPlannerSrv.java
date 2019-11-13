package com.egor.shoppingPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import com.egor.shoppingPlanner.service.ProductService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.egor.shoppingPlanner")
public class ShoppingPlannerSrv {

    public static void main(String[] args) {

        System.out.println("gfhgfhf");
        ConfigurableApplicationContext context = SpringApplication.run(ShoppingPlannerSrv.class);
        ProductService productService = context.getBean(ProductService.class);
        productService.addProduct("test34");
    }
}
