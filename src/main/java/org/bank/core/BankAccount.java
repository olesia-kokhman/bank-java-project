package org.bank.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private AccountCurrency currency;
    private String accumulations;
    private double cashback;
    private int creditLimit;
    private boolean isAvailableCreditLimit;

    @JsonCreator
    public BankAccount(@JsonProperty("balance") double balance,
                       @JsonProperty("currency") AccountCurrency currency,
                       @JsonProperty("accumulations") String accumulations,
                       @JsonProperty("cashback") double cashback,
                       @JsonProperty("account_number") String accountNumber,
                       @JsonProperty("credit_limit") int creditLimit,
                       @JsonProperty("is_available_credit_limit") boolean isAvailableCreditLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.accumulations = accumulations;
        this.cashback = cashback;
        this.creditLimit = creditLimit;
        this.isAvailableCreditLimit = isAvailableCreditLimit;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }
}
