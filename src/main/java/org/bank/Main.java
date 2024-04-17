package org.bank;

import org.bank.core.BankAccount;
import org.bank.statistics_generation.StatisticsGenerator;
import org.bank.parsing.JSONBankAccountParser;
import org.bank.statistics_generation.XMLStatisticsParser;

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

        String xmlFilePath = "C:\\internship\\statistics\\statistics_by_currency.xml";

        StatisticsGenerator generator = new StatisticsGenerator(allBankAccounts);
        XMLStatisticsParser.parseStatisticsToXML(xmlFilePath, generator.generateStatisticsByCurrency());

    }
}