package org.example.financesaccountmentwebapp.repositories;

import org.example.financesaccountmentwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * интерфейс репозитория для работы с пользователями
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * находит пользователя по имени пользователя
     *
     * @param username имя пользователя
     * @return пользователь с указанным именем или null, если не найден
     */
    User findByUsername(String username);
}