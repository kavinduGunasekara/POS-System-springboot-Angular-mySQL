package com.example.pos.repo;


import com.example.pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByActiveEquals(boolean activeState);
}
