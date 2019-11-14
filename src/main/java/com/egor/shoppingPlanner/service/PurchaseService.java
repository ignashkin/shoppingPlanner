package com.egor.shoppingPlanner.service;

import com.egor.shoppingPlanner.domain.PurchaseList;
import org.springframework.stereotype.Service;
import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseListService purchaseListService;

    Purchase getLastPurchase(Product product) {
        ArrayList<Purchase> purchases =(ArrayList<Purchase>) purchaseRepository.findByProductOrderByDate(product);
        Purchase purchase = null;
        if (!purchases.isEmpty()) {
            purchase = purchases.get(purchases.size()-1);
        }
        return purchase;
    }

    public long calculateTtl(Purchase purchase) {
        Product product =purchase.getProduct();
        ArrayList<Purchase> purchases =(ArrayList<Purchase>) purchaseRepository.findByProductOrderByDate(product);
        double sumValue = 0;
        long period = 0;
        Purchase firstPurchase;
        if (!purchases.isEmpty()) {
            for (Purchase purchaseCur : purchases ) {
                sumValue = sumValue + purchaseCur.getValue();
            }
            firstPurchase = purchases.get(0);
            period = (long) Math.ceil (DAYS.between(firstPurchase.getDate(),purchase.getDate()) / sumValue);
        }
        else {
            period = Long.MAX_VALUE;
        }

        purchase.setTtl((long)Math.ceil (period*purchase.getValue()));
        return purchase.getTtl();
    }
}
