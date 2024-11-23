package org.example.financesaccountmentwebapp.DTOS;

import java.util.List;

/**
 * DTO для категории
 */
public class CategoryDTO {
    private Long id;
    private String name;
    private Boolean income;
    private UserDTO user;
    private List<TransactionDTO> transactions;


    public CategoryDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}