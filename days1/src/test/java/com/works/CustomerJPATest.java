package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CustomerJPATest {

    @Autowired
    CustomerRepository customerRepository;

    @RepeatedTest(5)
    public void customerAdd() {
        Customer customer = new Customer();
        customer.setPrice(3000);
        customer.setTitle("TV");
        customerRepository.save(customer);
        System.out.println(customer.getCid());
        Assertions.assertNotEquals(0, customer.getCid());
    }

    @Test
    public void customerList() {
        addSample();
        List<Customer> list = customerRepository.findAll();
        System.out.println(list.size());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertNotEquals(0, list.size())
        );
    }

    @Test
    public void customerUpdate() {

        addSample();
        Customer customer = new Customer();
        customer.setCid(1l);
        customer.setTitle("TV");
        customer.setPrice(4000);

        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCid());
        if ( optionalCustomer.isPresent() ) {
            customerRepository.saveAndFlush(customer);
            Assertions.assertTrue(true);
        }else {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void search() {
        addSample();
        List<Customer> list = customerRepository.findByTitleContainsOrPriceBetweenAllIgnoreCase("title", 1, 1000);
        Assertions.assertNotEquals(0, list.size());
    }


    public void addSample() {
        for (int i = 0; i < 5; i++) {
            Customer customer = new Customer();
            customer.setTitle("Title: " + i);
            customer.setPrice(i * 10);
            customerRepository.save(customer);
        }
    }



}
