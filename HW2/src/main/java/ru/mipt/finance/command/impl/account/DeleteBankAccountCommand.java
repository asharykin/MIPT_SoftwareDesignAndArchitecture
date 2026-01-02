package ru.mipt.finance.command.impl.account;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DeleteBankAccountCommand extends BaseBankAccountCommand {
    private final Integer accountId;

    public DeleteBankAccountCommand(Integer accountId) {
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
