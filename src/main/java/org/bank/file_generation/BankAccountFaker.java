package org.bank.file_generation;

import com.github.javafaker.Faker;
import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BankAccountFaker {
    private static Faker faker = new Faker();

    public BankAccountFaker() {}

    public List<BankAccount> generateBankAccounts() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        int bankAccountNumber = getRandomRecordNumber();

        for(int i = 0; i < bankAccountNumber; i++) {
            BankAccount bankAccount = generateBankAccount();
            bankAccounts.add(bankAccount);
        }

        return bankAccounts;
    }

    public static BankAccount generateBankAccount() {
        String accountNumber = faker.numerify("################");
        double balance = Math.round((faker.random().nextDouble() * 100000) * 100.0) / 100.0;
        int currencyNumber = faker.random().nextInt(0, 2);
        String accumulations = String.format(Locale.US, "deposits=%.2f, bonds=%.2f, jars=%.2f",
                (Math.round((faker.random().nextDouble() * 100000) * 100.0) / 100.0),
                (Math.round((faker.random().nextDouble() * 100000) * 100.0) / 100.0),
                (Math.round((faker.random().nextDouble() * 100000) * 100.0) / 100.0));
        double cashback = (Math.round((faker.random().nextDouble() * 10000) * 100.0) / 100.0);
        int creditLimit = faker.random().nextInt(0, 1001) * 100;
        boolean isAvailableCreditLimit = faker.bool().bool();

        return new BankAccount(balance, AccountCurrency.values()[currencyNumber],
                accumulations, cashback, accountNumber, creditLimit, isAvailableCreditLimit);
    }

    private int getRandomRecordNumber() {
        Random random = new Random();
        return random.nextInt(0, 1000);
    }

}
