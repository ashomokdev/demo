package com.comeon.demo.utils;

import com.comeon.demo.model.EmailResult;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<EmailResult> convert(Object[][] data) {
        if (data != null && data.length > 0) {
            ArrayList<EmailResult> result = new ArrayList<>();
            for (Object[] o : data) {
                result.add(EmailResult.builder().email((String) o[0]).count((long) o[1]).build());
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
