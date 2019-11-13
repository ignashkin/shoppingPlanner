package com.egor.shoppingPlanner.service;

import com.egor.shoppingPlanner.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.domain.PurchaseList;
import com.egor.shoppingPlanner.repo.PurchaseListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class PurchaseListService {
    @Autowired
    PurchaseListRepository purchaseListRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductService productService;

    public PurchaseList getPurchaseList(LocalDate date) {
        PurchaseList purchaseList = purchaseListRepository.findByDate(date);
        if (purchaseList == null) {
            purchaseList = new PurchaseList(date);
            purchaseList.setPurchases(new HashSet<Purchase>());
            savePurchaseList(purchaseList);
        }
        return purchaseList;
    }

    public void planPurchaseList(LocalDate date) {
        ArrayList<Product> products = new ArrayList<Product>();
        Set<Purchase> purchases = new HashSet<Purchase>();
        products = productService.productsEnding(date);
        PurchaseList purchaseList = getPurchaseList(date);
        for (Product product : products) {
            purchases.add(new Purchase(product,purchaseList));
        }
        purchaseList.setPurchases(purchases);
        savePurchaseList(purchaseList);
    }

    public void savePurchaseList(PurchaseList purchaseList) {
        purchaseList.calculateCost();
        for (Purchase purchase : purchaseList.getPurchases()) {
            purchaseRepository.save(purchase);
        }
        purchaseListRepository.save(purchaseList);
    }

}
