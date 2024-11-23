package com.vastrika.backend.customer.service;

import java.util.regex.Pattern;

public class PasswordValidator{
    public static boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length()>16)
            return false;
        else {
            String uppercaseRegex = ".*[A-Z].*";
            String lowercaseRegex = ".*[a-z].*";
            String digitRegex = ".*\\d.*";
            String specialCharRegex = ".*[@#$%^&!/].*";

            boolean hasUppercase = Pattern.matches(uppercaseRegex, password);
            boolean hasLowercase = Pattern.matches(lowercaseRegex, password);
            boolean hasDigit = Pattern.matches(digitRegex, password);
            boolean hasSpecialChar = Pattern.matches(specialCharRegex, password);

            return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
        }
    }
}