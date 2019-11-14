package com.egor.shoppingPlanner.service;

import com.egor.shoppingPlanner.domain.PurchaseList;
import com.egor.shoppingPlanner.repo.PurchaseListRepository;
import org.springframework.stereotype.Service;
import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseListService purchaseListService;
    @Autowired
    PurchaseListRepository purchaseListRepository;

    public Purchase getLastPurchase(Product product) {
        ArrayList<PurchaseList> purchaseLists = purchaseListService.findPurchaseLists(product);
        Purchase lastPurchase = null;
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findByProduct(product);
        if (!purchaseLists.isEmpty()) {
          PurchaseList purchaseList   = purchaseLists.get(purchaseLists.size() - 1);
          for (Purchase purchase : purchases) {
              if (purchase.getPurchaseList().equals(purchaseList)) {
                  lastPurchase = purchase;
                  break;
              }
          }
        }
        return lastPurchase;
    }

    public long calculateTtl(Purchase purchase) {
        Product product = purchase.getProduct();
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findByProduct(product);
        List<PurchaseList> purchaseLists = purchaseListService.findPurchaseLists(product);
        double sumValue = 0;
        long ttl = 0;

        if (!purchases.isEmpty()) {
            for (Purchase purchaseCur : purchases) {
                sumValue = sumValue + purchaseCur.getValue();
            }
            LocalDate firstPurchaseDate = purchaseLists.get(0).getDate();
            if (firstPurchaseDate.isBefore(purchase.getPurchaseList().getDate())) {
                ttl = (long) Math.ceil(DAYS.between(firstPurchaseDate, purchase.getPurchaseList().getDate()) / sumValue);
            } else {
                LocalDate lastPurchaseDate = purchaseLists.get(purchaseLists.size() - 1).getDate();
                ttl = (long) Math.ceil(DAYS.between(purchase.getPurchaseList().getDate(), lastPurchaseDate) / sumValue);
            }
        } else {
            ttl = Long.MAX_VALUE;
        }
        purchase.setTtl(ttl);
        return purchase.getTtl();
    }
}
