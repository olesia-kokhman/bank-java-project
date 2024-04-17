package org.bank;

import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;
import org.bank.core.StatisticsGenerator;
import org.bank.parsing.JSONBankAccountParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String directoryFilePath = "C:\\internship\\bank-account-files-in-json\\";

        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles();
        List<BankAccount> allBankAccounts = new ArrayList<>();

        if(files != null) {
            for(File file: files) {
                List<BankAccount> parsedBankAccounts = JSONBankAccountParser.parseJSON2BankAccountList(
                        directoryFilePath + file.getName());
                allBankAccounts.addAll(parsedBankAccounts);
            }
        }

        StatisticsGenerator generator = new StatisticsGenerator(allBankAccounts);
        List<Integer> currencyList = generator.generateStatisticsByCurrency();
        for (Integer currencyCountNumber: currencyList) {
            System.out.println(currencyCountNumber);
        }

    }
}