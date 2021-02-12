package com.number.validation.controller;

import com.number.validation.model.CustomerResponse;
import com.number.validation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<List<CustomerResponse>> getCustomerNumbers(
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "valid", required = false) Boolean isValidNumber) {

        List<CustomerResponse> customerResponseList = customerService.getCustomers(country, isValidNumber);

        return ResponseEntity.ok(customerResponseList);
    }
}
