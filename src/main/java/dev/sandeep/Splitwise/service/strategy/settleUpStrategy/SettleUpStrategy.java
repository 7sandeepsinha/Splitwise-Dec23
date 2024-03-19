package dev.sandeep.Splitwise.service.strategy.settleUpStrategy;

import dev.sandeep.Splitwise.entity.Expense;
import dev.sandeep.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {
    List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses);
}
