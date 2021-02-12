package com.number.validation.model;

public enum Country {
    Cameroon("+237", "\\(237\\)\\ ?[2368]\\d{7,8}$", "\\(237\\) .*"),
    Ethiopia("+251", "\\(251\\)\\ ?[1-59]\\d{8}$", "\\(251\\) .*"),
    Morocco("+212", "\\(212\\)\\ ?[5-9]\\d{8}$", "\\(212\\) .*"),
    Mozambique("+258", "\\(258\\)\\ ?[28]\\d{7,8}$", "\\(258\\) .*"),
    Uganda("+256", "\\(256\\)\\ ?\\d{9}$", "\\(256\\) .*");

    private final String code;
    private final String phoneNumberRegex;
    private final String countryCodeRegex;

    Country(String code, String phoneNumberRegex, String countryCodeRegex) {
        this.code = code;
        this.phoneNumberRegex = phoneNumberRegex;
        this.countryCodeRegex = countryCodeRegex;
    }

    public String getCode() {
        return code;
    }

    public String getPhoneNumberRegex() {
        return phoneNumberRegex;
    }

    public String getCountryCodeRegex() {
        return countryCodeRegex;
    }
}
