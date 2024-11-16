package org.example.financesaccountmentwebapp.repositories;

import org.example.financesaccountmentwebapp.models.Transaction;
import org.example.financesaccountmentwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

}