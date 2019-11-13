package com.egor.shoppingPlanner.service;

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

    void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
    Purchase createPurchase(Product product) {
        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        savePurchase(purchase);
        return purchase;
    }

    Purchase getLastPurchase(Product product) {
        ArrayList<Purchase> purchases =(ArrayList<Purchase>) purchaseRepository.findByProductOrderByDate(product);
        LocalDate lastDate = LocalDate.of(2019,11,12);
        if (purchases != null) {
            for (Purchase purchase : purchases) {
                if (purchase.getDate().isAfter(lastDate)) {
                    lastDate = purchase.getDate();
                }
            }
        }
        return purchaseRepository.findByDate(lastDate);
    }

    public long calculateTtl(Product product) {
        ArrayList<Purchase> purchases =(ArrayList<Purchase>) purchaseRepository.findByProductOrderByDate(product);
        double interval = 0;
        long period = 0;
        Purchase currentPurchase;
        Purchase previousPurchase;
        if (purchases != null & purchases.size() > 1 ) {
            for (int i = purchases.size() - 1; i > 0; i--) {
                currentPurchase = purchases.get(i);
                previousPurchase = purchases.get(i-1);
                interval = interval + DAYS.between(previousPurchase.getDate(),currentPurchase.getDate()) / previousPurchase.getValue();
            }
            period = (long) Math.ceil(interval/(purchases.size()-1));
        }
        Purchase lastPurchase = getLastPurchase(product);
        float purchaseValue = lastPurchase.getValue();
        lastPurchase.setTtl((long) Math.ceil(period/purchaseValue));
        savePurchase(lastPurchase);
        return lastPurchase.getTtl();
    }
}
