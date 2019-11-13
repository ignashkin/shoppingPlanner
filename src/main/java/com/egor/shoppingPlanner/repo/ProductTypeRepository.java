package com.egor.shoppingPlanner.repo;

import org.springframework.data.repository.CrudRepository;
import com.egor.shoppingPlanner.domain.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

}
