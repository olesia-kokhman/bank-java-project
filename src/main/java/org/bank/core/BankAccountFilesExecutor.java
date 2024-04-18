package org.bank.core;

import org.bank.parsing.JSONBankAccountParser;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankAccountFilesExecutor {

    public static CopyOnWriteArrayList<BankAccount> load(int threadNumber, List<String> filePaths) {

        CopyOnWriteArrayList<BankAccount> allBankAccounts = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        for (String filePath : filePaths) {
            executorService.submit(() -> {
                allBankAccounts.addAll(JSONBankAccountParser.parseJSON2BankAccountList2(filePath));
            });
        }

        executorService.shutdown();

        try {
            boolean allTasksCompleted = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            if (!allTasksCompleted) {
                System.err.println("Some tasks did not terminate within the specified timeout.");
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        return allBankAccounts;
    }
}
