package org.example.financesaccountmentwebapp.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.CategoryRepository;
import org.example.financesaccountmentwebapp.repositories.TransactionRepository;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для управления категориями
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * отображает список категорий пользователя
     *
     * @param model модель для передачи данных в представление
     * @param session текущая сессия пользователя
     * @return страница категорий или страница логина при посещении страницы незалогиненным юзером
     */
    @GetMapping
    public String printCategories(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userRepository.findByUsername(username);
            List<Category> userCategories = categoryRepository.findByUser (user);
            List<Category> generalCategories = categoryRepository.findByUserIsNull();

            userCategories.addAll(generalCategories);
            model.addAttribute("categories", userCategories);
        } else {
            return "redirect:/login";
        }

        model.addAttribute("category", new Category());
        return "categories";
    }

    /**
     * добавляет новую категорию для пользователя
     *
     * @param name имя категории
     * @param session текущая сессия пользователя
     * @return редирект на страницу категорий
     */
    @PostMapping
    public String addCategory(@RequestParam String name, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userRepository.findByUsername(username);
            Category category = new Category();
            category.setName(name);
            category.setUser (user);
            categoryRepository.save(category);
        }
        return "redirect:/categories";
    }

    /**
     * удаляет категорию по id
     *
     * @param id id категории для удаления
     * @return редирект на страницу категорий
     */
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}