package org.example.financesaccountmentwebapp.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.example.financesaccountmentwebapp.services.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * контроллер для управления пользовательским интерфейсом
 */
@Controller
public class UserController {

    @Autowired
    private DiagramService diagramService;

    @Autowired
    private UserRepository userRepository;

    /**
     * отображает домашнюю страницу
     *
     * @param model модель для передачи данных в представление
     * @param session текущая сессия пользователя
     * @return домашнаяя страница или страница логина при ошибке
     */
    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userRepository.findByUsername(username);
            model.addAttribute("user", user);

            Map<String, Map<String, Double>> stats = diagramService.getTransactionsStatsForLastMonth(username);

            System.out.println("FULL CHART DATA: " + stats);

            stats.forEach((type, categories) -> {
                System.out.println(type + " Categories:");
                categories.forEach((category, amount) -> {
                    System.out.println(category + ": " + amount);
                });
            });

            // для фронта чарт жс
            model.addAttribute("incomeData", stats.get("income"));
            model.addAttribute("expenseData", stats.get("expenses"));
            model.addAttribute("chartData", stats);
        } else {
            return "redirect:/login";
        }
        return "home";
    }
}