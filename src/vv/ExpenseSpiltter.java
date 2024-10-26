/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vv;

/**
 *
 * @author trisha deshmukh
 */
import java.util.*;

public class ExpenseSpiltter {
    public static Map<String, Double> splitExpenses(Map<String, Double> expenses) {
        double totalSpent = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        int numPeople = expenses.size();
        double averagePerPerson = totalSpent / numPeople;

        Map<String, Double> balances = new HashMap<>();
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            String person = entry.getKey();
            double personTotal = entry.getValue();
            balances.put(person, personTotal - averagePerPerson);
        }

        return balances;
    }

    public static void printTransactions(Map<String, Double> balances) {
        List<Map.Entry<String, Double>> sortedBalances = new ArrayList<>(balances.entrySet());
        sortedBalances.sort(Map.Entry.comparingByValue());

        int left = 0;
        int right = sortedBalances.size() - 1;

        while (left < right) {
            Map.Entry<String, Double> debtor = sortedBalances.get(left);
            Map.Entry<String, Double> creditor = sortedBalances.get(right);

            double amount = Math.min(-debtor.getValue(), creditor.getValue());
            amount = Math.round(amount * 100.0) / 100.0; // Round to 2 decimal places

            System.out.printf("%s owes $%.2f to %s%n", debtor.getKey(), amount, creditor.getKey());

            debtor.setValue(debtor.getValue() + amount);
            creditor.setValue(creditor.getValue() - amount);

            if (Math.abs(debtor.getValue()) < 0.01) left++;
            if (Math.abs(creditor.getValue()) < 0.01) right--;
        }
    }
}
