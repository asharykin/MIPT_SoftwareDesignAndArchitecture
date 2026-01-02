package ru.mipt.finance.command.impl.account;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpdateBankAccountCommand extends BaseBankAccountCommand {
    private final Integer accountId;
    private final String newName;

    public UpdateBankAccountCommand(Integer accountId, String newName) {
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
