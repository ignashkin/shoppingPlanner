package com.egor.shoppingPlanner.repo;

import org.springframework.data.repository.CrudRepository;
import com.egor.shoppingPlanner.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
