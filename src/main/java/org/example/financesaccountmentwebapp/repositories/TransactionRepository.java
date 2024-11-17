package org.example.financesaccountmentwebapp.repositories;

import org.example.financesaccountmentwebapp.models.Transaction;
import org.example.financesaccountmentwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * интерфейс репозитория для работы с транзакциями
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * находит транзакции по пользователю
     *
     * @param user пользователь
     * @return список транзакций
     */
    List<Transaction> findByUser (User user);
}