package org.bank.parsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static void parseBankAccountList2JSON(List<BankAccount> bankAccounts, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), bankAccounts);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


}
