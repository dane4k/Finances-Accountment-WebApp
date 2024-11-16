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

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public String printCategories(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userRepository.findByUsername(username);
            List<Category> userCategories = categoryRepository.findByUser(user);
            List<Category> generalCategories = categoryRepository.findByUserIsNull();

            userCategories.addAll(generalCategories);
            model.addAttribute("categories", userCategories);
        } else {
            return "redirect:/login";
        }

        model.addAttribute("category", new Category());
        return "categories";
    }

    @PostMapping
    public String addCategory(@RequestParam String name, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userRepository.findByUsername(username);
            Category category = new Category();
            category.setName(name);
            category.setUser(user);
            categoryRepository.save(category);
        }
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}