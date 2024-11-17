package org.example.financesaccountmentwebapp.models;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * класс для финансовой транзакции
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    /**
     * уникальный идентификатор транзакции
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * пользователь, связанный с транзакцией
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * категория транзакции
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * дата транзакции
     */
    private LocalDate date;

    /**
     * сумма транзакции
     */
    private Double amount;

    /**
     * тип транзакции
     */
    private Boolean income;

    /**
     * конструктор по умолчанию
     */
    public Transaction() {

    }

    /**
     * получает уникальный идентификатор
     *
     * @return уникальный идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * устанавливает уникальный идентификатор
     *
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * получает пользователя
     *
     * @return пользователь
     */
    public User getUser () {
        return user;
    }

    /**
     * устанавливает пользователя
     *
     * @param user пользователь
     */
    public void setUser (User user) {
        this.user = user;
    }

    /**
     * получает категорию
     *
     * @return категория
     */
    public Category getCategory() {
        return category;
    }

    /**
     * устанавливает категорию
     *
     * @param category категория
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * получает дату
     *
     * @return дата
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * устанавливает дату
     *
     * @param date дата
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * получает сумму
     *
     * @return сумма
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * устанавливает сумму
     *
     * @param amount сумма
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * получает признак доходности
     *
     * @return true если доход, иначе false
     */
    public Boolean getIncome() {
        return income;
    }

    /**
     * устанавливает признак доходности
     *
     * @param income true если доход, иначе false
     */
    public void setIncome(Boolean income) {
        this.income = income;
    }
}