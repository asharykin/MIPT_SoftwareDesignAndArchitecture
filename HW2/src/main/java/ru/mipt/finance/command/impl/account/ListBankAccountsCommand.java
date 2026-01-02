package ru.mipt.finance.command.impl.account;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mipt.finance.model.BankAccount;

import java.util.List;

@Component
@Scope("prototype")
public class ListBankAccountsCommand extends BaseBankAccountCommand {

    @Override
    public void execute() {
        List<BankAccount> accounts = facade.getAllBankAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No bank accounts found.");
            return;
        }

        System.out.println("\nBank Accounts:");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }
}
