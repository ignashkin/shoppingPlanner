package com.egor.shoppingPlanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.domain.PurchaseList;
import com.egor.shoppingPlanner.repo.PurchaseListRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class PurchaseListService {
    @Autowired
    PurchaseListRepository purchaseListRepository;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    ProductService productService;

    void createPurchaseList(LocalDate date) {
        PurchaseList purchaseList = new PurchaseList();
        ArrayList<Product> products = new ArrayList<Product>();
        Set<Purchase> purchases = (Set<Purchase>) new ArrayList<Purchase>();
        products = productService.productsEnding(date);
        for (Product product : products) {
            purchases.add(purchaseService.createPurchase(product));
        }
        purchaseList.setPurchases(purchases);
        purchaseList.setDate(date);
    }
}
