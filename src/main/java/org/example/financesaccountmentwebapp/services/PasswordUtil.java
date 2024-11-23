package org.example.financesaccountmentwebapp.services;

import org.mindrot.jbcrypt.BCrypt;

/**
 * утилита для хеширования и проверки пароля
 */
public class PasswordUtil {

    /**
     * хеширует пароль с использованием BCrypt
     *
     * @param password пароль для хеширования
     * @return хешированный пароль
     */
    public static String hashPassword(String password) {
        var result = BCrypt.hashpw(password, BCrypt.gensalt());
        return result;
    }

    /**
     * проверяет, соответствует ли введенный пароль хешированному паролю
     *
     * @param password введенный пароль
     * @param hashedPassword хешированный пароль
     * @return true, если пароли совпадают, иначе false
     */
    public static boolean isCorrect(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}