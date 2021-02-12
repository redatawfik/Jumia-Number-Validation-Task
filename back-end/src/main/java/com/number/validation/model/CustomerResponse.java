package com.number.validation.model;

import com.number.validation.service.NumberValidationUtils;

public class CustomerResponse extends Customer implements Comparable<CustomerResponse> {

    private Country country;
    private String countryCode;
    private boolean isValidNumber;

    public CustomerResponse(Customer customer) {
        super(customer);

        initialize();
    }

    public CustomerResponse(int id, String name, String phone) {
        super(id, name, phone);

        initialize();
    }

    private void initialize() {
        setCountry();
        setCountryCode();
        setValidNumber();
    }

    private void setCountry() {
        this.country = NumberValidationUtils.getCountryForNumber(getPhone());
    }

    private void setCountryCode() {
        this.countryCode = NumberValidationUtils.getCountryCode(this.country);
    }

    private void setValidNumber() {
        this.isValidNumber = NumberValidationUtils.isValidNumber(getPhone());
    }

    public Country getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public boolean isValidNumber() {
        return isValidNumber;
    }

    @Override
    public int compareTo(CustomerResponse otherCustomer) {
        if (country != otherCustomer.getCountry()) {
            return country.toString().compareTo(otherCustomer.getCountry().toString());
        } else {
            return isValidNumber ? -1 : 1;
        }
    }
}
