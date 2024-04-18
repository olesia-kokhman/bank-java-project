package org.bank.parsing;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bank.core.AccountCurrency;
import org.bank.core.BankAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONBankAccountParser {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void parseBankAccountList2JSON(List<BankAccount> bankAccounts, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), bankAccounts);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static List<BankAccount> parseJSON2BankAccountList2(String filePath) {

        List<BankAccount> bankAccounts = new ArrayList<>();
        JsonFactory factory = new JsonFactory();

        try(JsonParser jsonParser = factory.createParser(new File(filePath))) {

            if(jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IOException();
            }

            while(jsonParser.nextToken() != JsonToken.END_ARRAY) {
                JsonNode node = objectMapper.readTree(jsonParser);

                String accountNumber = node.has("account_number") ? node.get("account_number").asText() : null;
                AccountCurrency currency = node.has("currency") ? AccountCurrency.valueOf(node.get("currency").asText()) : null;
                String categories = node.has("categories") ? node.get("categories").asText() : null;
                Integer creditLimit = node.has("credit_limit") ? node.get("credit_limit").asInt() : null;


                if(!isValidIsAvailableCreditLimit(node) || !isValidBalance(node)) {
                    continue;
                }
                boolean isAvailableCreditLimit = node.get("is_available_credit_limit").asBoolean();
                double balance = node.get("balance").asDouble();

                bankAccounts.add(new BankAccount(accountNumber, balance, currency, categories, creditLimit, isAvailableCreditLimit));

            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return bankAccounts;
    }


    private static boolean isValidIsAvailableCreditLimit(JsonNode node) {
        boolean isValid = false;

        if (node.has("is_available_credit_limit")) {
            JsonNode availableCreditLimitNode = node.get("is_available_credit_limit");
            if (availableCreditLimitNode.isBoolean()) {
                isValid = true;
            }
        }
        return isValid;
    }

    private static boolean isValidBalance(JsonNode node) {
        boolean isValid = false;

        if (node.has("balance")) {
            JsonNode balanceNode = node.get("balance");
            if (balanceNode.isDouble()) {
                isValid = true;
            }
        }
        return isValid;
    }



}
