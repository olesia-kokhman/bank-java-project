package org.bank.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount {

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("currency")
    private AccountCurrency currency;

    @JsonProperty("categories")
    private String categories;

    @JsonProperty("credit_limit")
    private int creditLimit;

    @JsonProperty("is_available_credit_limit")
    private boolean isAvailableCreditLimit;

    //@JsonCreator
    public BankAccount(String accountNumber,
                       double balance,
                       AccountCurrency currency,
                       String categories,
                       int creditLimit,
                       boolean isAvailableCreditLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.categories = categories;
        this.creditLimit = creditLimit;
        this.isAvailableCreditLimit = isAvailableCreditLimit;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }

    public boolean getIsAvailableCreditLimit() {
        return isAvailableCreditLimit;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public double getBalance() {
        return balance;
    }

    public String getCategories() {
        return categories;
    }

//    @JsonProperty("account_number") String accountNumber,
//    @JsonProperty("balance") double balance,
//    @JsonProperty("currency") AccountCurrency currency,
//    @JsonProperty("categories") String categories,
//    @JsonProperty("credit_limit") int creditLimit,
//    @JsonProperty("is_available_credit_limit") boolean isAvailableCreditLimit

}
