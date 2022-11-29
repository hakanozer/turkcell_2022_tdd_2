package com.works.repositories;

import com.works.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByTitleContainsOrPriceBetweenAllIgnoreCase(String title, Integer priceStart, Integer priceEnd);

    Page<List<Customer>> findByTitleContainsAllIgnoreCase(String title, Pageable pageable);

}