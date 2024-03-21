package dev.sandeep.Splitwise.service;

import dev.sandeep.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface GroupService {
    List<SettlementTransaction> settleUp(int groupId);
}
