package org.example.financesaccountmentwebapp.RestControllers;

import org.example.financesaccountmentwebapp.services.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/home/transactions")
public class DiagramsDataRestController {

    private final DiagramService diagramService;

    @Autowired
    public DiagramsDataRestController(DiagramService diagramService) {
        this.diagramService = diagramService;
    }

    /**
     * получение данных для диаграммы
     * @param username ник
     * @return мапа данных
     */
    @GetMapping("/stats")
    public Map<String, Map<String, Double>> getTransactionStats(@RequestParam String username) {
        return diagramService.getTransactionsStatsForLastMonth(username);
    }
}
