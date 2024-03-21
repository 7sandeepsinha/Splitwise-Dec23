package dev.sandeep.Splitwise.service.strategy.settleUpStrategy;

import dev.sandeep.Splitwise.dto.UserAmount;
import dev.sandeep.Splitwise.entity.*;

import java.util.*;

public class MinimumTransactionSettlementStrategy implements SettleUpStrategy{
    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        HashMap<User, Double> map = getOutStandingBalances(expenses);
        // Comparator for min heap (ascending order)
        Comparator<UserAmount> minHeapComparator = Comparator.comparingDouble(UserAmount::getAmount);
        // Comparator for max heap (descending order)
        Comparator<UserAmount> maxHeapComparator = Comparator.comparingDouble(UserAmount::getAmount).reversed();
        // Max heap
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(maxHeapComparator);
        // Min heap
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(minHeapComparator);

        for(Map.Entry<User, Double> entry : map.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            } else if(entry.getValue() > 0){
                maxHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            } else {
                System.out.println("user does not need to participate in settle up");
            }
        }

        List<SettlementTransaction> settlementTransactions = new ArrayList<>();


        while(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            UserAmount borrower = minHeap.poll();
            UserAmount lendor = maxHeap.poll();


            if(Math.abs(borrower.getAmount()) > lendor.getAmount()){
                //Lendor = 500, Borrower = -1000 , borrower pays lendor 500
                borrower.setAmount(borrower.getAmount() + lendor.getAmount());
                minHeap.add(borrower);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lendor.getUser(), lendor.getAmount());
                settlementTransactions.add(settlementTransaction);
            } else if(Math.abs(borrower.getAmount()) < lendor.getAmount()){
                //Lendor = 1000, Borrower = -500
                lendor.setAmount(lendor.getAmount() + borrower.getAmount());
                maxHeap.add(lendor);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lendor.getUser(), Math.abs(borrower.getAmount()) );
                settlementTransactions.add(settlementTransaction);
            } else { // Math.abs(borrower.getAmount()) == lendor.getAmount()
                //Lendor = 500, Borrower = -500
                //Transaction -> Borrower to Lendor 500, and both are free from settle up
                System.out.println("Do nothing, both are equal");
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lendor.getUser(), lendor.getAmount());
                settlementTransactions.add(settlementTransaction);
            }
        }
        return settlementTransactions;
    }

    private HashMap<User, Double> getOutStandingBalances(List<Expense> expenses){
        HashMap<User,Double> expenseMap = new HashMap<>();
        for(Expense expense : expenses){
            for(UserExpense userExpense: expense.getUserExpenses()){
                User participant = userExpense.getUser();
                double amount = userExpense.getAmount();
                if(expenseMap.containsKey(participant)){
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        expenseMap.put(participant, expenseMap.get(participant) + amount);
                    } else{
                        expenseMap.put(participant, expenseMap.get(participant) - amount);
                    }
                } else {
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        expenseMap.put(participant, 0 + amount);
                    } else{
                        expenseMap.put(participant, 0 - amount);
                    }
                }
            }
        }
        return expenseMap;
    }
}
/*
       1. Go through all the expenses, and find the total outstanding for each person
       2. All borrowers will go to a min heap
       3. All lendors will go to a max heap
       4. pull min from minHeap and max from maxHeap, and create a transaction
       5. Update the balances, put them back in heap
       6. Keep doing until heap is empty.


       Lendor = 500, Borrower = -500
       make both of them zero, and keep both of them out of the heap

       Lendor = 1000, Borrower = -500
       borrower will become zero, lendor will become 500 and lendor will go inside the heap again

       Lendor = 500, Borrower = -1000
       Lendor will become zero, Borrower will become -500 and Borrower will go inside the heap again
 */