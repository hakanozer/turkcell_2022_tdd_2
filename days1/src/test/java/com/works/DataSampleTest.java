package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
public class DataSampleTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Sql(value = "classpath:customerSample.sql")
    public void dataSample() {
        List<Customer> list = customerRepository.findAll();
        System.out.println(list);
        Assertions.assertTrue( list.size() > 0 );
    }

}
