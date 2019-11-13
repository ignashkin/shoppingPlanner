package com.egor.shoppingPlanner.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class PurchaseList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    LocalDate date;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product") // mappedBy ???
    private Set<Purchase> purchases;

    protected  PurchaseList() {

    }

    public PurchaseList(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }


    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }
}
