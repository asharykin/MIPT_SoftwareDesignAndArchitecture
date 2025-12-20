package ru.mipt.finance.command.impl.account;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.BankAccountFacade;

public class UpdateBankAccountCommand implements Command {
    private final BankAccountFacade facade;

    private final Integer accountId;
    private final String newName;

    public UpdateBankAccountCommand(BankAccountFacade facade, Integer accountId, String newName) {
        this.facade = facade;
        this.accountId = accountId;
        this.newName = newName;
    }

    @Override
    public void execute() {
        boolean updated = facade.updateBankAccount(accountId, newName);
        if (updated) {
            System.out.println("Account updated successfully.");
        } else {
            System.out.println("Account not found or could not be updated.");
        }
    }
}
