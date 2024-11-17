package org.example.financesaccountmentwebapp.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.Transaction;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.CategoryRepository;
import org.example.financesaccountmentwebapp.repositories.TransactionRepository;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * контроллер для управления транзакциями
 */
@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * отображает таблицу транзакций пользователя
     *
     * @param type тип транзакции (доход или расход)
     * @param model модель для передачи данных в представление
     * @param session текущая сессия пользователя
     * @return страница транзакций или логина при отсутствии юзернейма в логине
     */
    @GetMapping
    public String printTransactions(@RequestParam(required = false) String type, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username != null) {
            User user = userRepository.findByUsername(username);
            List<Transaction> userTransactions = transactionRepository.findByUser (user);

            if ("true".equals(type)) {
                userTransactions = userTransactions.stream()
                        .filter(transaction -> transaction.getIncome())
                        .toList();
            } else if ("false".equals(type)) {
                userTransactions = userTransactions.stream()
                        .filter(transaction -> !transaction.getIncome())
                        .toList();
            }

            model.addAttribute("transactions", userTransactions);

            List<Category> userCategories = categoryRepository.findByUser (user);
            List<Category> generalCategories = categoryRepository.findByUserIsNull();
            List<Category> availableCategories = new ArrayList<>(userCategories);
            availableCategories.addAll(generalCategories);

            model.addAttribute("categories", availableCategories);
        } else {
            return "redirect:/login";
        }

        model.addAttribute("transaction", new Transaction());

        return "transactions";
    }

    /**
     * добавляет новую транзакцию
     *
     * @param amount сумма транзакции
     * @param date дата транзакции
     * @param income тип транзакции (доход или расход)
     * @param categoryId идентификатор категории
     * @param session текущая сессия пользователя
     * @return редирект на страницу транзакций
     */
    @PostMapping
    public String addTransaction(@RequestParam Double amount,
                                 @RequestParam LocalDate date,
                                 @RequestParam Boolean income,
                                 @RequestParam("category.id") Long categoryId,
                                 HttpSession session) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setIncome(income);

        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsername(username);
        transaction.setUser (user);

        Category category = categoryRepository.findById(categoryId).orElse(null);
        transaction.setCategory(category);

        transactionRepository.save(transaction);
        return "redirect:/transactions";
    }

    /**
     * удаляет транзакцию по идентификатору
     *
     * @param id идентификатор транзакции для удаления
     * @return редирект на страницу транзакций
     */
    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
        return "redirect:/transactions";
    }
}