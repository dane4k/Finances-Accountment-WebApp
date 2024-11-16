package org.example.financesaccountmentwebapp.services;

import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.DiagramRepository;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiagramService {

    @Autowired
    private DiagramRepository diagramRepository;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Map<String, Double>> getTransactionsStatsForLastMonth(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        List<Object[]> results = diagramRepository.findTransactionSummaryForUser(
                startDate, endDate, user.getId()
        );

        Map<String, Double> incomeMap = new HashMap<>();
        Map<String, Double> expenseMap = new HashMap<>();

        for (Object[] result : results) {
            String categoryName = (String) result[0];
            Double sumAmount = ((Number) result[1]).doubleValue();
            Boolean income = (Boolean) result[2];

            System.out.println("Processing: Category=" + categoryName +
                    ", Amount=" + sumAmount +
                    ", Income=" + income);

            if (Boolean.TRUE.equals(income)) {
                incomeMap.merge(categoryName, sumAmount, Double::sum);
            } else {
                expenseMap.merge(categoryName, sumAmount, Double::sum);
            }
        }

        System.out.println("Final Income Map: " + incomeMap);
        System.out.println("Final Expense Map: " + expenseMap);

        Map<String, Map<String, Double>> resultMap = new HashMap<>();
        resultMap.put("income", incomeMap);
        resultMap.put("expenses", expenseMap);

        return resultMap;


    }
}