package com.number.validation.service;


import com.number.validation.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(String country, Boolean valid);
}
