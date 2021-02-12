package com.number.validation.service;


import com.number.validation.model.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getCustomers(String country, Boolean valid);
}
