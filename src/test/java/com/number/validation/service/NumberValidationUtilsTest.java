package com.number.validation.service;

import com.number.validation.model.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberValidationUtilsTest {

    private final String VALID_CAMERON_PHONE_NUMBER = "(237) 699209115";
    private final String INVALID_ETHIOPIA_PHONE_NUMBER = "251) 9119454961";

    @Test
    void getCountryWithValidNumber() {
        Country countryForNumber = NumberValidationUtils.getCountryForNumber(VALID_CAMERON_PHONE_NUMBER);

        assertEquals(countryForNumber, Country.Cameroon);
    }

    @Test
    void isValidNumberWithValidNumber() {
        boolean isValid = NumberValidationUtils.isValidNumber(VALID_CAMERON_PHONE_NUMBER);

        assertTrue(isValid);
    }

    @Test
    void isValidNumberWithInvalidNumber() {
        boolean isValid = NumberValidationUtils.isValidNumber(INVALID_ETHIOPIA_PHONE_NUMBER);

        assertFalse(isValid);
    }

    @Test
    public void getCountryCode() {
        String countryCode = NumberValidationUtils.getCountryCode(Country.Morocco);

        assertEquals(countryCode, Country.Morocco.getCode());
    }

}