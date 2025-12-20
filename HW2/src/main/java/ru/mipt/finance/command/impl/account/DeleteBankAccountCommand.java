package ru.mipt.finance.command.impl.account;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.BankAccountFacade;

public class DeleteBankAccountCommand implements Command {
    private final BankAccountFacade facade;

    private final Integer accountId;

    public DeleteBankAccountCommand(BankAccountFacade facade, Integer accountId) {
        this.facade = facade;
        this.accountId = accountId;
    }

    @Override
    public void execute() {
        boolean deleted = facade.deleteBankAccount(accountId);
        if (deleted) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found or could not be deleted.");
        }
    }
}
