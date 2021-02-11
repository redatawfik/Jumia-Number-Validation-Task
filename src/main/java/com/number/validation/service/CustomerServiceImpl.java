package com.number.validation.service;

import com.number.validation.dao.CustomerDao;
import com.number.validation.model.Country;
import com.number.validation.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getCustomers(String country, Boolean valid) {
        List<Customer> customerList = customerDao.getAll();

        if (country != null) {
            customerList = getCustomersForCountry(customerList, country);
        }

        if (valid == null) {
            return customerList;
        } else if (valid) {
            return getCustomersWithValidNumber(customerList);
        } else {
            return getCustomersWithInvalidNumbers(customerList);
        }
    }

    private List<Customer> getCustomersForCountry(List<Customer> customerList, String countryText) {

        List<Customer> result = new ArrayList<>();

        Country country = getCountryFromText(countryText);
        if (country == null) return result;

        for (Customer customer : customerList) {
            if (customer.getCountry() == country) {
                result.add(customer);
            }
        }

        return result;
    }

    private Country getCountryFromText(String countryText) {
        if (countryText == null) return null;
        for (Country country : Country.values()) {
            if (country.toString().equals(countryText)) {
                return country;
            }
        }
        return null;
    }


    private List<Customer> getCustomersWithValidNumber(List<Customer> customerList) {
        List<Customer> validCustomerNumbers = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.isValidNumber()) {
                validCustomerNumbers.add(customer);
            }
        }
        return validCustomerNumbers;
    }

    private List<Customer> getCustomersWithInvalidNumbers(List<Customer> customerList) {
        List<Customer> validCustomerNumbers = new ArrayList<>();
        for (Customer customer : customerList) {
            if (!customer.isValidNumber()) {
                validCustomerNumbers.add(customer);
            }
        }
        return validCustomerNumbers;
    }
}
