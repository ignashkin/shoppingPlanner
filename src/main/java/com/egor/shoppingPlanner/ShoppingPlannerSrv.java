package com.egor.shoppingPlanner;

import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.domain.PurchaseList;
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
        PurchaseListService purchaseListService =context.getBean(PurchaseListService.class);

        Product product1 = productService.addProduct("test1");
        Product product2 = productService.addProduct("test2");
        PurchaseList purchaseList1 = purchaseListService.getPurchaseList(LocalDate.of(2019, 10,1));
        Purchase purchase11 = productService.createPurchase(product1, purchaseList1);
        purchaseList1.addPurchase(purchase11);
        purchaseService.calculateTtl(purchase11);
        Purchase purchase12 = productService.createPurchase(product2, purchaseList1);
        purchaseList1.addPurchase(purchase12);
        purchaseService.calculateTtl(purchase12);
        purchaseListService.savePurchaseList(purchaseList1);
        System.out.println("Список 1 \n" +  purchaseList1.toString());
        PurchaseList purchaseList2 = purchaseListService.getPurchaseList(LocalDate.of(2019, 10,11));
        Purchase purchase21 = productService.createPurchase(product1,purchaseList2);
        purchaseList2.addPurchase(purchase21);
        purchaseService.calculateTtl(purchase21);
        Purchase purchase22 = productService.createPurchase(product2, purchaseList2);
        purchaseList2.addPurchase(purchase22);
        purchaseService.calculateTtl(purchase22);
        purchaseListService.savePurchaseList(purchaseList2);
        System.out.println("Список 2 \n" + purchaseList2.toString());
        //purchase1.setCost(10.5f);
        //purchase2.setCost(20f);
        PurchaseList purchaseListPlain = purchaseListService.planPurchaseList(LocalDate.of(2019,10,21));
        purchaseListService.savePurchaseList(purchaseListPlain);
        System.out.print("Список план \n" + purchaseListPlain.toString());
    }
}
