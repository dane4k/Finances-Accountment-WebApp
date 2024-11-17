package org.example.financesaccountmentwebapp.models;

import jakarta.persistence.*;

import java.util.List;

/**
 * класс, представляющий категорию финансовых транзакций
 */
@Entity
@Table(name = "categories")
public class Category {

    /**
     * уникальный идентификатор категории, автоинкремент
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * пользователь, связанный с данной категорией
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * список транзакций, связанных с данной категорией
     */
    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;

    /**
     * название категории
     */
    private String name;

    /**
     * признак, является ли категория доходной или расходной (true и false соответственно)
     */
    private Boolean income;

    /**
     * получает уникальный идентификатор категории
     *
     * @return уникальный идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * устанавливает уникальный идентификатор категории
     *
     * @param id уникальный идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * получает список транзакций, связанных с данной категорией
     *
     * @return список транзакций
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * устанавливает список транзакций для данной категории
     *
     * @param transactions список транзакций
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * получает название категории
     *
     * @return название категории
     */
    public String getName() {
        return name;
    }

    /**
     * устанавливает название категории
     *
     * @param name название категории
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * получает тип категории.
     *
     * @return true, если категория доходная; false, если расходная
     */
    public Boolean getIncome() {
        return income;
    }

    /**
     * устанавливает признак доходности категории.
     *
     * @param income true, если категория доходная; false, если расходная
     */
    public void setIncome(Boolean income) {
        this.income = income;
    }

    /**
     * устанавливает пользователя, связанного с данной категорией
     *
     * @param user пользователь
     */
    public void setUser (User user) {
        this.user = user;
    }
}