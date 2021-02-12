package com.number.validation.service;

import com.number.validation.model.Customer;
import com.number.validation.model.CustomerResponse;
import com.number.validation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> getCustomers(String country, Boolean state) {
        List<Customer> customerList = customerRepository.findAll();

        List<CustomerResponse> customerResponseList = getCustomerResponse(customerList);


        if (!ObjectUtils.isEmpty(country)) {
            customerResponseList = filterCustomersByCountry(customerResponseList, country);
        }

        if (state != null) {
            customerResponseList = filterCustomersByState(customerResponseList, state);
        }

        Collections.sort(customerResponseList);
        return customerResponseList;
    }

    private List<CustomerResponse> getCustomerResponse(List<Customer> customerList) {
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerResponse customerResponse = new CustomerResponse(customer);
            customerResponseList.add(customerResponse);
        }

        return customerResponseList;
    }

    private List<CustomerResponse> filterCustomersByCountry(List<CustomerResponse> customerResponseList, String country) {

        return customerResponseList
                .stream()
                .filter(customer -> customer.getCountry().toString().equalsIgnoreCase(country.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<CustomerResponse> filterCustomersByState(List<CustomerResponse> customerResponseList, boolean state) {

        return customerResponseList.stream()
                .filter(c -> c.isValidNumber() == state).collect(Collectors.toList());
    }
}
