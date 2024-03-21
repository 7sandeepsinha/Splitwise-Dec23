package dev.sandeep.Splitwise.dto;

import dev.sandeep.Splitwise.entity.User;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class UserAmount {
    private User user;
    private double amount;

    public UserAmount(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }
}
