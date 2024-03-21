package dev.sandeep.Splitwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserExpense extends BaseModel{
    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;

    public UserExpense() {
    }

    public UserExpense(User user, double amount, UserExpenseType userExpenseType) {
        this.user = user;
        this.amount = amount;
        this.userExpenseType = userExpenseType;
    }
}
