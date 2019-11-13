package com.egor.shoppingPlanner;

import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.repo.ProductRepository;
import com.egor.shoppingPlanner.repo.PurchaseRepository;
import com.egor.shoppingPlanner.service.PurchaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import com.egor.shoppingPlanner.service.ProductService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.egor.shoppingPlanner")
public class ShoppingPlannerSrv {

    public static void main(String[] args) {

        System.out.println("gfhgfhf");
        ConfigurableApplicationContext context = SpringApplication.run(ShoppingPlannerSrv.class);
        ProductService productService = context.getBean(ProductService.class);
        PurchaseService purchaseService = context.getBean(PurchaseService.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        productService.addProduct("test34");
        Purchase purchase = purchaseService.createPurchase(productRepository.getByName("test34"));
        purchase.setDate(LocalDate.now());
        purchaseService.savePurchase(purchase);

    }
}
