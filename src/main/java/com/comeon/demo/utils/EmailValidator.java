package com.comeon.demo.utils;

import java.util.ArrayList;

public class EmailValidator {
    public static final ArrayList<String> validDomains = new ArrayList<>();

    static {
        validDomains.add("@comeon.com");
        validDomains.add("@cherry.se");
    }

    public static boolean isValid(String email) {
        for (String validDomain : validDomains) {
            if (email.endsWith(validDomain)) {
                return true;
            }
        }
        return false;
    }
}
