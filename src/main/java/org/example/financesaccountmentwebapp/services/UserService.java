package org.example.financesaccountmentwebapp.services;

import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.CategoryRepository;

public class UserService {

    private CategoryRepository categoryRepository;

    public void createCategoryForUser (User user, String categoryName, Boolean isIncome) {
        Category category = new Category();
        category.setName(categoryName);
        category.setIncome(isIncome);
        category.setUser (user);
        categoryRepository.save(category);
    }
}
