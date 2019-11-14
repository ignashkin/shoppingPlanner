package com.egor.shoppingPlanner.repo;

import com.egor.shoppingPlanner.domain.Purchase;
import org.springframework.data.repository.CrudRepository;
import com.egor.shoppingPlanner.domain.PurchaseList;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PurchaseListRepository extends CrudRepository<PurchaseList, Long> {
    PurchaseList findByDate(LocalDate date);
}
