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

        String directoryFilePath = args[0];
        String statisticsAttribute = args[1]; // check for the correctness of arguments

        //String directoryFilePath = "C:\\internship\\bank-account-files-in-json\\";

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
        String xmlFilePath = String.format("C:\\internship\\statistics\\statistics_by_%s.xml", statisticsAttribute);

        switch (statisticsAttribute) {
            case "currency":
                XMLStatisticsParser.parseStatisticsToXML(xmlFilePath, generator.generateStatisticsByCurrency());
                break;
            case "is_available_credit_limit":
                XMLStatisticsParser.parseStatisticsToXML(xmlFilePath, generator.generateStatisticsByIsAvailableCreditLimit());
                break;
            case "credit_limit":
                XMLStatisticsParser.parseStatisticsToXML(xmlFilePath, generator.generateStatisticsByCreditLimit());
                break;
            case "categories":
                XMLStatisticsParser.parseStatisticsToXML(xmlFilePath, generator.generateStatisticsByCategories());
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + statisticsAttribute);
        }

    }
}