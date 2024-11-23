package org.example.financesaccountmentwebapp.RestControllers;

import org.example.financesaccountmentwebapp.DTOS.CategoryDTO;
import org.example.financesaccountmentwebapp.DTOS.TransactionDTO;
import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.Transaction;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.CategoryRepository;
import org.example.financesaccountmentwebapp.repositories.TransactionRepository;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * добавление транзакции
     * @param username ник
     * @param transactionDTO дто транзакции
     * @return результат запроса
     */
    @PostMapping("/{username}")
    public ResponseEntity<String> addTransaction(@PathVariable String username, @RequestBody TransactionDTO transactionDTO) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        }

        Transaction transaction = new Transaction();
        transaction.setUser (user);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDate(transactionDTO .getDate());
        transaction.setIncome(transactionDTO.getIncome());

        
        if (transactionDTO.getCategory() != null) {
            Category category = categoryRepository.findById(transactionDTO.getCategory().getId()).orElse(null);
            transaction.setCategory(category);
        }

        transactionRepository.save(transaction);
        return new ResponseEntity<>("Транзакция добавлена", HttpStatus.CREATED);
    }

    /**
     * получение транзакций по нику
     * @param username ник
     * @return список транзакций
     */
    @GetMapping("/{username}")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Transaction> transactions = transactionRepository.findByUser (user);
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(transactionDTOs, HttpStatus.OK);
    }

    /**
     * удаление транзакции по id
     * @param username ник
     * @param transactionId id транзакции
     * @return результат запроса
     */
    @DeleteMapping("/{username}/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String username, @PathVariable Long transactionId) {
        User user = userRepository.findByUsername(username);
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null || !transaction.getUser ().equals(user)) {
            return new ResponseEntity<>("Транзакция не найдена или вы не можете её удалить", HttpStatus.BAD_REQUEST);
        }
        transactionRepository.delete(transaction);
        return new ResponseEntity<>("Транзакция удалена", HttpStatus.OK);
    }

    /**
     * конверт модели транзакции в модель дто для передачи данных
     * @param transaction
     * @return
     */
    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setAmount(transaction.getAmount());
        dto.setIncome(transaction.getIncome());

        
        if (transaction.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(transaction.getCategory().getId());
            categoryDTO.setName(transaction.getCategory().getName());
            dto.setCategory(categoryDTO);
        }

        return dto;
    }
}