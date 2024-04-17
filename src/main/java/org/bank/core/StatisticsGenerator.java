package org.bank.core;

import java.util.ArrayList;
import java.util.List;

public class StatisticsGenerator {

    List<BankAccount> bankAccounts;

    public StatisticsGenerator(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts; // ???
    }

    public List<Integer> generateStatisticsByCurrency() {
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

        List<Integer> currencyList = new ArrayList<>(3);
        currencyList.add(counterUAN);
        currencyList.add(counterUSD);
        currencyList.add(counterEURO);

        return currencyList;
    }


}
