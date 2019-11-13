package com.egor.shoppingPlanner.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.egor.shoppingPlanner.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(long id);
}
