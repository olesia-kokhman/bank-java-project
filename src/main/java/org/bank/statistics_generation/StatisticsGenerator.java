package org.bank.statistics_generation;

import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.util.*;

public class StatisticsGenerator {

    private final List<BankAccount> bankAccounts;

    public StatisticsGenerator(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts; // ???
    }

    public Map<String, Integer> generateStatisticsByCurrency() {
        int counterUAN = 0;
        int counterUSD = 0;
        int counterEURO = 0;
        for(BankAccount account: bankAccounts) {
            AccountCurrency currency = account.getCurrency();
            if(currency == AccountCurrency.UAN) {
                counterUAN++;
            } else if (currency == AccountCurrency.USD) {
                counterUSD++;
            } else if (currency == AccountCurrency.EURO) {
                counterEURO++;
            }
        }

        Map<String, Integer> currencyInfo = new HashMap<>();
        currencyInfo.put(AccountCurrency.UAN.name(), counterUAN);
        currencyInfo.put(AccountCurrency.USD.name(), counterUSD);
        currencyInfo.put(AccountCurrency.EURO.name(), counterEURO);

        return currencyInfo;
    }

    public Map<String, Integer> generateStatisticsByIsAvailableCreditLimit() {
        int trueCounter = 0;
        int falseCounter = 0;

        for(BankAccount account: bankAccounts) {
            boolean accountIsAvailableCreditLimit = account.getIsAvailableCreditLimit();
            if(accountIsAvailableCreditLimit) {
                trueCounter++;
            } else {
                falseCounter++;
            }
        }

        Map<String, Integer> isAvailableCreditLimitInfo = new HashMap<>();
        isAvailableCreditLimitInfo.put(String.valueOf(Boolean.TRUE), trueCounter);
        isAvailableCreditLimitInfo.put(String.valueOf(Boolean.FALSE), falseCounter);

        return isAvailableCreditLimitInfo;

    }

    public Map<String, Integer> generateStatisticsByCreditLimit() {
        int creditTakenCounter = 0;
        int creditNotTakenCounter = 0;

        for (BankAccount account : bankAccounts) { // lambda
            double balance = account.getBalance();
            int creditLimit = account.getCreditLimit();
            boolean isAvailableCreditLimit = account.getIsAvailableCreditLimit();

            if (!isAvailableCreditLimit) {
                creditNotTakenCounter++;
            } else {
                double realBalance = balance - creditLimit;

                if (realBalance >= 0) {
                    creditNotTakenCounter++;
                } else {
                    creditTakenCounter++;
                }
            }
        }

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

            for(int i = 0; i < splittedCategories.length; i++) {
                String category = splittedCategories[i];
                if(categoryInfo.containsKey(category)) {
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
