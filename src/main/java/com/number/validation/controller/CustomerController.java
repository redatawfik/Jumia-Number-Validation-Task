package com.number.validation.controller;

import com.number.validation.model.Customer;
import com.number.validation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/")
    public ResponseEntity<List<Customer>> getCustomerNumbers(
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "valid", required = false) Boolean isValidNumber) {

        List<Customer> customerList = customerService.getCustomers(country, isValidNumber);

        return ResponseEntity.ok(customerList);
    }
}
