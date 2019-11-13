package com.egor.shoppingPlanner.repo;

import org.springframework.data.repository.CrudRepository;
import com.egor.shoppingPlanner.domain.PurchaseList;

public interface PurchaseListRepository extends CrudRepository<PurchaseList, Long> {
}
