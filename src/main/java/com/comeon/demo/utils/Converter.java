package com.comeon.demo.utils;

import com.comeon.demo.model.Email;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<Email> convert(Object[][] data) {
        if (data != null && data.length > 0) {
            ArrayList<Email> result = new ArrayList<>();
            for (Object[] o : data) {
                result.add(Email.builder().email((String) o[0]).count((long) o[1]).build());
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
