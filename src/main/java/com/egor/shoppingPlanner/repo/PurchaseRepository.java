package com.egor.shoppingPlanner.repo;

import org.springframework.stereotype.Repository;
import com.egor.shoppingPlanner.domain.Product;
import com.egor.shoppingPlanner.domain.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Iterable<Purchase> findByProductOrderByDate(Product product);

    Purchase findByDate(LocalDate date);
}
