package br.com.devhernandesandrade.event_locator.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 20;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");

    public static boolean isPasswordSecure(String password) {

        return password.length() >= MIN_LENGTH &&
               password.length() <= MAX_LENGTH &&
               UPPERCASE_PATTERN.matcher(password).find() &&
               LOWERCASE_PATTERN.matcher(password).find() &&
               DIGIT_PATTERN.matcher(password).find();
    }

}
