package com.egor.shoppingPlanner.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    String name;
    @ManyToMany
    @JoinTable(name="type_product",
            joinColumns = @JoinColumn(name="type_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName="id")
    )
    private Set<Product> products;
}
