package org.bank;

import org.bank.core.BankAccount;
import org.bank.core.BankAccountFilesExecutor;
import org.bank.file_generation.BankAccountFaker;
import org.bank.parsing.JSONBankAccountParser;
import org.bank.statistics_generation.StatisticsGenerator;
import org.bank.statistics_generation.XMLStatisticsParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {

        String directoryFilePath = args[0];
        String statisticsAttribute = args[1];

        List<String> fullPathFiles = new ArrayList<>();
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles();

        if(files == null) {
            throw new NullPointerException();
        }

        for(File file: files) {
            fullPathFiles.add(directoryFilePath + file.getName());
        }

        CopyOnWriteArrayList<BankAccount> allBankAccounts = BankAccountFilesExecutor.load(8, fullPathFiles);

        StatisticsGenerator generator = new StatisticsGenerator(allBankAccounts);
        String xmlFilePath = String.format("./src//main/resources/statistics_by_%s.xml", statisticsAttribute);

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