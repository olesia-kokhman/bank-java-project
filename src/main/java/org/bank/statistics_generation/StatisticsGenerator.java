package org.bank.statistics_generation;

import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.util.*;

public class StatisticsGenerator {

    private final List<BankAccount> bankAccounts;

    public StatisticsGenerator(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Map<String, Integer> generateStatisticsByCurrency() {
        Map<String, Integer> currencyInfo = new HashMap<>();

        for (AccountCurrency currency : AccountCurrency.values()) {
            currencyInfo.put(currency.name(), 0);
        }

        for (BankAccount account : bankAccounts) {
            AccountCurrency currency = account.getCurrency();
            currencyInfo.put(currency.name(), currencyInfo.get(currency.name()) + 1);
        }

        return currencyInfo;
    }

    public Map<String, Integer> generateStatisticsByIsAvailableCreditLimit() {
        Map<String, Integer> isAvailableCreditLimitInfo = new HashMap<>();

        isAvailableCreditLimitInfo.put(String.valueOf(Boolean.TRUE), 0);
        isAvailableCreditLimitInfo.put(String.valueOf(Boolean.FALSE), 0);

        for (BankAccount account : bankAccounts) {


            boolean isAvailableCreditLimit = account.getIsAvailableCreditLimit();
            isAvailableCreditLimitInfo.put(String.valueOf(isAvailableCreditLimit),
                    isAvailableCreditLimitInfo.get(String.valueOf(isAvailableCreditLimit)) + 1);
        }

        return isAvailableCreditLimitInfo;
    }

    public Map<String, Integer> generateStatisticsByCreditLimit() {
        int creditTakenCounter = (int) bankAccounts.stream()
                .filter(account -> {
                    double balance = account.getBalance();
                    int creditLimit = account.getCreditLimit();
                    boolean isAvailableCreditLimit = account.getIsAvailableCreditLimit();
                    return isAvailableCreditLimit && (balance - creditLimit) < 0;
                })
                .count();

        int creditNotTakenCounter = (int) bankAccounts.stream()
                .filter(account -> {
                    double balance = account.getBalance();
                    int creditLimit = account.getCreditLimit();
                    boolean isAvailableCreditLimit = account.getIsAvailableCreditLimit();
                    return !isAvailableCreditLimit || (balance - creditLimit) >= 0;
                })
                .count();

        Map<String, Integer> creditLimitInfo = new HashMap<>();
        creditLimitInfo.put("Credit is taken", creditTakenCounter);
        creditLimitInfo.put("Credit is not taken", creditNotTakenCounter);

        return creditLimitInfo;
    }

    public Map<String, Integer> generateStatisticsByCategories() {
        Map<String, Integer> categoryInfo = new HashMap<>();

        for(BankAccount account: bankAccounts) {
            String categories = account.getCategories();
            String[] splittedCategories = categories.split(", ");

            for (String category : splittedCategories) {
                if (categoryInfo.containsKey(category)) {
                    int counter = categoryInfo.get(category);
                    counter++;
                    categoryInfo.put(category, counter);
                } else {
                    categoryInfo.put(category, 1);
                }
            }
        }

        return categoryInfo;
    }


}
