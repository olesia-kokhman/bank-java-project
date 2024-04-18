package org.bank.parsing;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONBankAccountParser {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static List<BankAccount> parseJSON2BankAccountList(String filePath) {

        List<BankAccount> bankAccounts = new ArrayList<>();
        try {
            bankAccounts = objectMapper.readValue(new File(filePath), new TypeReference<List<BankAccount>>() {});
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }

        return bankAccounts;
    }

    public static List<BankAccount> parseJSON2BankAccountList2(String filePath) {
        List<BankAccount> bankAccounts = new ArrayList<>();

        JsonFactory factory = new JsonFactory();

        try(JsonParser jsonParser = factory.createParser(new File(filePath))) {

            if(jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IOException();
            }
            int counter = 0;

            while(jsonParser.nextToken() != JsonToken.END_ARRAY) {
                JsonNode node = objectMapper.readTree(jsonParser);

                String accountNumber = node.has("account_number") ? node.get("account_number").asText() : null;
                Double balance = node.has("balance") ? node.get("balance").asDouble() : null;
                AccountCurrency currency = node.has("currency") ? AccountCurrency.values()[node.get("currency").asInt()] : null;
                String categories = node.has("categories") ? node.get("categories").asText() : null;
                Integer creditLimit = node.has("credit_limit") ? node.get("credit_limit").asInt() : null;
                Boolean isAvailableCreditLimit = node.has("is_available_credit_limit") ?
                        node.get("is_available_credit_limit").asBoolean() : null;

                if(accountNumber == null || balance == null || currency == null || categories == null || creditLimit == null
                || isAvailableCreditLimit == null) {
                    counter++;
                    continue;
                } else {
                    bankAccounts.add(new BankAccount(accountNumber, balance, currency, categories, creditLimit, isAvailableCreditLimit));
                }

            }
            System.out.println(counter);

        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return bankAccounts;
    }


}
