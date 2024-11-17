package org.example.financesaccountmentwebapp.repositories;

import org.example.financesaccountmentwebapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * интерфейс репозитория для работы с транзакциями
 */
public interface DiagramRepository extends JpaRepository<Transaction, Long> {

    /**
     * запрос для получения данных о транзакциях пользователя за определенное время для построения диаграмм
     *
     * @param startDate дата начала периода
     * @param endDate дата конца периода
     * @param userId уникальный идентификатор пользователя
     * @return список объектов, содержащих имя категории, сумму и признак доходности
     */
    @Query("SELECT " +
            "t.category.name, " +
            "SUM(t.amount), " +
            "t.income " +
            "FROM Transaction t " +
            "WHERE t.date >= :startDate AND t.date < :endDate " +
            "AND t.user.id = :userId " +
            "GROUP BY t.category.name, t.income")
    List<Object[]> findTransactionSummaryForUser (
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("userId") Long userId);
}