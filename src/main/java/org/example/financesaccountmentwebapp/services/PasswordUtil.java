package org.example.financesaccountmentwebapp.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean isCorrect(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
