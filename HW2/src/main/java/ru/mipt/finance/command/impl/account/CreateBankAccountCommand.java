package ru.mipt.finance.command.impl.account;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.model.BankAccount;

import java.math.BigDecimal;

public class CreateBankAccountCommand implements Command {
    private final BankAccountFacade facade;

    private final String name;
    private final BigDecimal initialBalance;

    public CreateBankAccountCommand(BankAccountFacade facade, String name, BigDecimal initialBalance) {
        this.facade = facade;
        this.name = name;
        this.initialBalance = initialBalance;
    }

    @Override
    public void execute() {
        BankAccount account = facade.createBankAccount(name, initialBalance);
        System.out.println("Account created successfully with ID: " + account.getId());
    }
}
