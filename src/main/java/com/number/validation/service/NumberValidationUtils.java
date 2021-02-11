package com.number.validation.service;

import com.number.validation.model.Country;

import java.util.regex.Pattern;

public class NumberValidationUtils {
    public static Country getCountryForNumber(String phoneNumber) {

        for (Country country : Country.values()) {
            String regex = country.getCountryCodeRegex();
            if (Pattern.matches(regex, phoneNumber)) {
                return country;
            }
        }

        return null;
    }

    public static boolean isValidNumber(String phoneNumber) {

        for (Country country : Country.values()) {
            String regex = country.getPhoneNumberRegex();
            if (Pattern.matches(regex, phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    public static String getCountryCode(Country country) {
        for (Country c : Country.values()) {
            if (c == country) {
                return c.getCode();
            }
        }
        return null;
    }
}
