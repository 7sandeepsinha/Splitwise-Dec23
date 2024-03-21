package dev.sandeep.Splitwise.controller;

import dev.sandeep.Splitwise.entity.SettlementTransaction;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {

    @GetMapping("/settleup/{groupId}")
    public ResponseEntity settleUp(@PathVariable("groupId") int groupId){
        List<SettlementTransaction> settlementTransactionList = new ArrayList<>();
        return ResponseEntity.ok(settlementTransactionList);
    }
}
