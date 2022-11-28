package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public Customer customerAdd( Customer customer ) {
        try {
            customerRepository.save(customer);
            return customer;
        }catch (Exception ex) {
            return null;
        }
    }

    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    public Page<List<Customer>> pageableCustomer(String title, int page) {
        Pageable pageable = PageRequest.of(page, 2);
        return customerRepository.findByTitleContainsAllIgnoreCase(title, pageable );
    }

}
