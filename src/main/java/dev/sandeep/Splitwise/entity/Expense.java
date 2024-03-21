package dev.sandeep.Splitwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String description;
    private double amount;
    private LocalDateTime expenseTime;
    private Currency currency;
    @ManyToOne
    private User addedBy;
    @OneToMany
    private List<UserExpense> userExpenses;

    public Expense() {
    }

    public Expense(String description, double amount, LocalDateTime expenseTime, Currency currency, User addedBy, List<UserExpense> userExpenses) {
        this.description = description;
        this.amount = amount;
        this.expenseTime = expenseTime;
        this.currency = currency;
        this.addedBy = addedBy;
        this.userExpenses = userExpenses;
    }
}
