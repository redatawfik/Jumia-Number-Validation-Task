package com.number.validation.model;

import com.number.validation.service.NumberValidationUtils;

public class Customer {
    private final int id;
    private final String name;
    private final String phoneNumber;
    private Country country;
    private String countryCode;
    private boolean isValidNumber;

    public Customer(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;

        setCountry(phoneNumber);
        setCountryCode();
        setValidNumber(phoneNumber);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Country getCountry() {
        return country;
    }

    private void setCountry(String phoneNumber) {
        this.country = NumberValidationUtils.getCountryForNumber(phoneNumber);
    }

    public String getCountryCode() {
        return countryCode;
    }

    private void setCountryCode() {
        this.countryCode = NumberValidationUtils.getCountryCode(this.country);
    }

    public boolean isValidNumber() {
        return isValidNumber;
    }

    private void setValidNumber(String phoneNumber) {
        this.isValidNumber = NumberValidationUtils.isValidNumber(phoneNumber);
    }
}
