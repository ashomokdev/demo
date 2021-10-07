package com.comeon.demo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class EmailValidatorTest {

    @Test
    public void testIsValid() {
        assertFalse(EmailValidator.isValid("test@test.com"));
        assertTrue(EmailValidator.isValid("test@comeon.com"));
        assertTrue(EmailValidator.isValid("test@cherry.se"));

    }
}