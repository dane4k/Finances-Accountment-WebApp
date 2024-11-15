package org.example.financesaccountmentwebapp.controllers;

import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.example.financesaccountmentwebapp.services.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.example.financesaccountmentwebapp.services.PasswordUtil.isCorrect;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegisterForm(@RequestParam String username, @RequestParam String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = new User();

        user.setUsername(username);
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("error", "Пользователь не найден");
            return "login";
        } else if (!isCorrect(password, user.getPassword())) {
            model.addAttribute("error", "Неверный пароль");
        }
        return "redirect:/home";
    }
}
