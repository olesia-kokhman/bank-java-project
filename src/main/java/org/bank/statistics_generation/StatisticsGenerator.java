package org.bank.statistics_generation;

import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsGenerator {

    List<BankAccount> bankAccounts;

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


}
