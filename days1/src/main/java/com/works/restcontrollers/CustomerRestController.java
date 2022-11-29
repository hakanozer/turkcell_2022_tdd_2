package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService  customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        Customer customer1 = customerService.customerAdd(customer);
        if (customer1 != null) {
            return new ResponseEntity<>(customer1, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Customer>> list() {
        List<Customer> list = customerService.customers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/page/{title}/{page}")
    public ResponseEntity<Page<List<Customer>>> pageable(@PathVariable String title, @PathVariable int page) {
        Page<List<Customer>> listPage = customerService.pageableCustomer(title, page);
        return new ResponseEntity(listPage, HttpStatus.OK);
    }

}
