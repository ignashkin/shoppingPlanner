package com.egor.shoppingPlanner.service;

import com.egor.shoppingPlanner.domain.PurchaseList;
import com.egor.shoppingPlanner.repo.PurchaseListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import com.egor.shoppingPlanner.repo.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PurchaseListRepository purchaseListRepository;

    @Autowired
    PurchaseListService purchaseListService;

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
        return products;
    }

    public Product addProduct(String name) {
        Product product = new Product(name);
        productRepository.save(product);
        return product;
    }

    void calculatePrice(Product product) {
        Purchase purchase = purchaseService.getLastPurchase(product);
        float price = purchase.getCost() * purchase.getValue();
        productRepository.save(product);
    }

    ArrayList<Product> productsEnding(LocalDate date) {
        ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
        ArrayList<Product> productsEnding = new ArrayList<Product>();

        for (Product product : products) {
            ArrayList<PurchaseList> purchaseLists =purchaseListService.findPurchaseLists(product);
            Purchase lastPurchase = purchaseService.getLastPurchase(product);
            PurchaseList lastPurchaseList = purchaseLists.get(purchaseLists.size()-1);
            if (lastPurchase != null && DAYS.between(lastPurchaseList.getDate(), date) > lastPurchase.getTtl() * lastPurchase.getValue() - 2) { //think about it
                productsEnding.add(product);
            }
        }
        return productsEnding;
    }

    public Purchase createPurchase(Product product, PurchaseList purchaseList) {
        Purchase purchase = new Purchase(product, purchaseList);
        purchaseList.addPurchase(purchase);
        return purchase;
    }
}
