package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addTest() {
        Customer customer = new Customer();
        String title = UUID.randomUUID().toString();
        customer.setTitle(title);
        customer.setPrice(3000);

        final Customer customerFinal = customerService.customerAdd(customer);
        Assertions.assertAll(
                () ->  Assertions.assertNotNull(customerFinal),
                () -> Assertions.assertNotEquals(0, customer.getCid())
        );
    }

    @Test
    public void listTest() {
        List<Customer> list = customerService.customers();
        Assertions.assertNotEquals(0, list.size());
    }

    @Test
    public void searchPage() throws JsonProcessingException {
        Page<List<Customer>> pageLists = customerService.pageableCustomer("a", 0);
        String data = objectMapper.writeValueAsString(pageLists);
        System.out.println(data);
        long count = pageLists.get().count();
        Assertions.assertEquals(2, count );
    }

}
