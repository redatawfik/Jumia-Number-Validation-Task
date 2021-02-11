package com.number.validation.service;

import com.number.validation.model.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class NumberValidationUtils {

    private static final Map<Country, String> countryMap = new HashMap<>();
    private static final List<String> validNumberRegex = new ArrayList<>();
    private static final Map<Country, String> countryCodeMap = new HashMap<>();

    static {
        countryMap.put(Country.Cameroon, "\\(237\\).*");
        countryMap.put(Country.Ethiopia, "\\(251\\).*");
        countryMap.put(Country.Morocco, "\\(212\\).*");
        countryMap.put(Country.Mozambique, "\\(258\\).*");
        countryMap.put(Country.Uganda, "\\(256\\).*");
    }

    static {
        validNumberRegex.add("\\(237\\)\\ ?[2368]\\d{7,8}$");
        validNumberRegex.add("\\(251\\)\\ ?[1-59]\\d{8}$");
        validNumberRegex.add("\\(212\\)\\ ?[5-9]\\d{8}$");
        validNumberRegex.add("\\(258\\)\\ ?[28]\\d{7,8}$");
        validNumberRegex.add("\\(256\\)\\ ?\\d{9}$");
    }

    static {
        countryCodeMap.put(Country.Cameroon, "+237");
        countryCodeMap.put(Country.Ethiopia, "+251");
        countryCodeMap.put(Country.Morocco, "+212");
        countryCodeMap.put(Country.Mozambique, "+258");
        countryCodeMap.put(Country.Uganda, "+256");
    }


    public static Country getCountryForNumber(String phoneNumber) {

        for (Map.Entry<Country, String> entry : countryMap.entrySet()) {
            Country country = entry.getKey();
            String regex = entry.getValue();

            if (Pattern.matches(regex, phoneNumber)) {
                return country;
            }
        }

        return null;
    }

    public static boolean isValidNumber(String phoneNumber) {
        for (String regex : validNumberRegex) {
            if (Pattern.matches(regex, phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    public static String getCountryCode(Country country) {
        return countryCodeMap.getOrDefault(country, "");
    }
}
