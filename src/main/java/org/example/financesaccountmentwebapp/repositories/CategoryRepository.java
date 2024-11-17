package org.example.financesaccountmentwebapp.repositories;

import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * интерфейс репозитория для работы с категориями
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * находит категории по пользователю
     *
     * @param user пользователь
     * @return список категорий
     */
    List<Category> findByUser (User user);

    /**
     * находит категории без привязки к пользователю
     *
     * @return список категорий
     */
    List<Category> findByUserIsNull();
}