package com.egor.shoppingPlanner.service;

import com.egor.shoppingPlanner.domain.PurchaseList;
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
            Purchase lastPurchase = purchaseService.getLastPurchase(product);
            if (lastPurchase != null && DAYS.between(lastPurchase.getDate(), date) > lastPurchase.getTtl() * lastPurchase.getValue() - 2) { //think about it
                productsEnding.add(product);
            }
        }
        return productsEnding;
    }

    public Purchase createPurchase(Product product, PurchaseList purchaseList) {
        Purchase purchase = new Purchase(product, purchaseList);
        purchase.setDate(purchaseList.getDate());
        return purchase;
    }
}
