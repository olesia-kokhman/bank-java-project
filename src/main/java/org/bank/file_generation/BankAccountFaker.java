package org.bank.file_generation;

import com.github.javafaker.Faker;
import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.util.*;

public class BankAccountFaker {
    private static Faker faker = new Faker();

    private static final List<String> CATEGORIES = Arrays.asList(
            "groceries", "health", "eating out", "transport", "beauty",
            "clothes", "charity", "bills", "home", "education", "travel", "digital",
            "entertainment", "electronics", "sport"
    );

    public BankAccountFaker() {}

    public static List<BankAccount> generateBankAccounts() {
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
        String categories = generateCategories();
        int creditLimit = faker.random().nextInt(0, 1001) * 100;
        boolean isAvailableCreditLimit = faker.bool().bool();

        return new BankAccount(balance, AccountCurrency.values()[currencyNumber],
                categories, accountNumber, creditLimit, isAvailableCreditLimit);
    }

    private static String generateCategories() {
        Random random = new Random();
        int numberOfCategories = random.nextInt(1, CATEGORIES.size());

        StringJoiner joiner = new StringJoiner(", ");

        List<String> categoriesCopy = new ArrayList<>(CATEGORIES);

        for(int i = 0; i < numberOfCategories; i++) {
            int index = random.nextInt(0, categoriesCopy.size());
            joiner.add(categoriesCopy.get(index));
            categoriesCopy.remove(index);
        }

        return joiner.toString();
    }

    private static int getRandomRecordNumber() {
        Random random = new Random();
        return random.nextInt(0, 1000);
    }

}
