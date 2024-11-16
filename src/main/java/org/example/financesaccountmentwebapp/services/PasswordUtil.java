package org.example.financesaccountmentwebapp.services;
import org.mindrot.jbcrypt.BCrypt;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//public class PasswordUtil {
//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public static String hashPassword(String password) {
//        return encoder.encode(password);
//    }
//
//    public static boolean isCorrect(String password, String hashedPassword) {
//        return encoder.matches(password, hashedPassword);
//    }
//}

public class PasswordUtil {
    public static String hashPassword(String password) {
        System.out.println("Hashing password: " + password);
        var result = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hashed password: " + result);
        return result;
    }

    public static boolean isCorrect(String password, String hashedPassword) {
        System.out.println("Checking password: " + password + ", hashed password: " + hashedPassword);
        return BCrypt.checkpw(password, hashedPassword);
    }
}