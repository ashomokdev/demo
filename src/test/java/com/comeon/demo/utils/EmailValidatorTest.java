package com.comeon.demo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class EmailValidatorTest {

    @Test
    public void testIsValid() {
        assertFalse(EmailValidator.isValid("test@test.com"));
        assertTrue(EmailValidator.isValid("test@comeon.com"));
        assertTrue(EmailValidator.isValid("test@cherry.se"));

    }
}