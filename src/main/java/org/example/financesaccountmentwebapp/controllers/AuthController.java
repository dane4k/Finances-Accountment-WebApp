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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

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
        if (userRepository.findByUsername(username) != null) {
            return "redirect:/register?error=usernameExists";
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = new User();

        user.setUsername(username);
        user.setPassword(hashedPassword);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register?error=registrationFailed";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping({"", "/"})
    public String showHome(Model model) {
        return "register";
    }

    @GetMapping("/logout")
    public String showLogout(Model model) {
        return "redirect:/register";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Пользователь не найден");
            return "redirect:/login"; // Redirect back to login
        } else if (!isCorrect(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Неверный пароль");
            return "redirect:/login"; // Redirect back to login
        }

        redirectAttributes.addFlashAttribute("username", user.getUsername());
        return "redirect:/home"; // Redirect to home
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        String username = (String) model.getAttribute("username"); // This will be null
        User user = userRepository.findByUsername(username); // You need to handle this
        model.addAttribute("user", user);
        return "home";
    }


}
