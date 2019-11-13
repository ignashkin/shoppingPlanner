package com.egor.shoppingPlanner.domain;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private float cost;
    private float value;
    private String shop;
    private LocalDate date;
    private  long ttl;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

   // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "purchaseList_id")
    //private PurchaseList purchaseList;

    public long getId() {
        return id;
    }

   // public PurchaseList getPurchaseList() {
     //   return purchaseList;
   // }

    //public void setPurchaseList(PurchaseList purchaseList) {
      //  this.purchaseList = purchaseList;
    //}

    public void setId(long id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
