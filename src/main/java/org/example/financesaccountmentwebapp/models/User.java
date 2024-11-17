package org.example.financesaccountmentwebapp.models;

import jakarta.persistence.*;

import java.util.List;

/**
 * сущность пользователя
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * уникальный идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * уникальное имя пользователя
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * пароль пользователя
     */
    @Column(nullable = false)
    private String password;

    /**
     * список транзакций пользователя
     */
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    /**
     * конструктор по умолчанию
     */
    public User() {
    }

    /**
     * получает список транзакций
     *
     * @return список транзакций
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * устанавливает список транзакций
     *
     * @param transactions список транзакций
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
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
     * получает имя пользователя
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * устанавливает имя пользователя
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * получает пароль
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * устанавливает пароль
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }
}