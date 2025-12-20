package ru.mipt.finance.builder;

import ru.mipt.finance.model.BankAccount;

import java.math.BigDecimal;

public class BankAccountBuilder {
    private Integer id;
    private String name;
    private BigDecimal balance;

    public BankAccountBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public BankAccountBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BankAccountBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(id, name, balance);
    }
}
