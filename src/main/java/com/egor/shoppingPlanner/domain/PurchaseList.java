package com.egor.shoppingPlanner.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class PurchaseList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate date;
    private float cost;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "purchaseList") // mappedBy ???
    private Set<Purchase> purchases;

    protected PurchaseList() {

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
        this.cost = this.cost + purchase.getCost();
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        String purchaseList = "id: " + Long.toString(this.id) + "  date: " + this.date.toString() + "\n";
        for (Purchase purchase : this.purchases) {
            purchaseList = purchaseList + purchase.toString() + "\n";
        }
        purchaseList = purchaseList + "Cost: " + Float.toString(this.cost) + "\n";
        return purchaseList;
    }
}
