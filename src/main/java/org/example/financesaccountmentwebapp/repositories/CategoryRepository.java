package org.example.financesaccountmentwebapp.repositories;

import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);

    List<Category> findByUserIsNull();
}