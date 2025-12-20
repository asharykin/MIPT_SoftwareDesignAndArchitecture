package ru.mipt.finance.command.impl.account;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.model.BankAccount;

import java.util.List;

public class ListAccountsCommand implements Command {
    private final BankAccountFacade facade;

    public ListAccountsCommand(BankAccountFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        List<BankAccount> accounts = facade.getAllBankAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No bank accounts found.");
            return;
        }

        System.out.println("\nBank Accounts:");
        for (BankAccount account : accounts) {
            System.out.println(account.getId() + " | " + account.getName() + " | Balance: " + account.getBalance());
        }
    }
}
