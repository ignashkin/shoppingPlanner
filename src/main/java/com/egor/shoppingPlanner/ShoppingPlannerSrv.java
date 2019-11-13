package com.egor.shoppingPlanner;

import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.domain.PurchaseList;
import com.egor.shoppingPlanner.repo.ProductRepository;
import com.egor.shoppingPlanner.repo.PurchaseRepository;
import com.egor.shoppingPlanner.service.PurchaseListService;
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
        ShoppingPlannerSrv shoppingPlannerSrv = new ShoppingPlannerSrv();
        shoppingPlannerSrv.run();
    }

    void run() {
        ConfigurableApplicationContext context = SpringApplication.run(ShoppingPlannerSrv.class);
        ProductService productService = context.getBean(ProductService.class);
        PurchaseService purchaseService = context.getBean(PurchaseService.class);
        //ProductRepository productRepository = context.getBean(ProductRepository.class);
        PurchaseListService purchaseListService =context.getBean(PurchaseListService.class);

        Product product1 = productService.addProduct("test1");
        Product product2 = productService.addProduct("test2");
        Product product3 = productService.addProduct("test3");
        PurchaseList purchaseList = purchaseListService.getPurchaseList(LocalDate.now());
        Purchase purchase1 = new Purchase(product1, purchaseList);
        Purchase purchase2 = new Purchase(product1,purchaseList);
        purchaseList.addPurchase(purchase1);
        purchaseList.addPurchase(purchase2);
        purchase1.setCost(10.5f);
        /*
        System.out.println(purchaseService.createPurchase(product1).toString());
        System.out.println(purchaseService.createPurchase(product2).toString());
        System.out.println(purchaseService.createPurchase(product3).toString());
        */
        purchaseListService.savePurchaseList(purchaseList);
        System.out.print(purchaseList.toString());
    }
}
