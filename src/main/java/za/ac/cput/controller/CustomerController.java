package za.ac.cput.controller;

/* CustomerController.java
Controller for Customer
Author: David Henriques Garrancho (221475982)
Date: 17 June 2023
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/read/{id}")
    public Customer read(@PathVariable String id) {
        return customerService.read(id);
    }

    @PutMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return customerService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Customer> getAll() {
        return customerService.getAll();
    }
}
