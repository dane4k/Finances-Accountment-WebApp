package org.example.financesaccountmentwebapp.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.example.financesaccountmentwebapp.services.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.example.financesaccountmentwebapp.services.PasswordUtil.isCorrect;

/**
 * контроллер для аутентификации пользователей
 */
@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    /**
     * отображает форму регистрации
     *
     * @return страница регистрации
     */
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    /**
     * обрабатывает форму регистрации
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param model модель для передачи данных в представление
     * @return перенаправление на страницу входа или отображение ошибки
     */
    @PostMapping("/register")
    public String processRegisterForm(@RequestParam String username, @RequestParam String password, Model model) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Пользователь уже существует");
            return "register";
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ошибка регистрации");
            return "register";
        }

        return "redirect:/login";
    }

    /**
     * отображает форму входа
     *
     * @return страница входа
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * домашня страница
     *
     * @param model модель для передачи данных в представление
     * @return имя представления для домашней страницы
     */
    @GetMapping({"", "/"})
    public String showHome(Model model) {
        return "register";
    }

    /**
     * обрабатывает выход пользователя
     *
     * @param session текущая сессия пользователя
     * @return редирект на страницу входа
     */
    @GetMapping("/logout")
    public String showLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * обрабатывает форму входа
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param redirectAttributes атрибуты для перенаправления для обработки ошибок при логине
     * @param session текущая сессия пользователя
     * @return редирект на домашнюю страницу или форму входа с ошибкой
     */
    @PostMapping("/login")
    public String processLoginForm(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Пользователь не найден");
            return "redirect:/login";
        } else if (!isCorrect(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Неверный пароль");
            return "redirect:/login";
        }
        session.setAttribute("username", user.getUsername());
        return "redirect:/home";
    }
}