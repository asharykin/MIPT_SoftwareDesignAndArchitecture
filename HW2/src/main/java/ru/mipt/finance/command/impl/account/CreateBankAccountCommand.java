package ru.mipt.finance.command.impl.account;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mipt.finance.model.BankAccount;

import java.math.BigDecimal;

@Component
@Scope("prototype")
public class CreateBankAccountCommand extends BaseBankAccountCommand {
    private final String name;
    private final BigDecimal initialBalance;

    public CreateBankAccountCommand(String name, BigDecimal initialBalance) {
        this.name = name;
        this.initialBalance = initialBalance;
    }

    @Override
    public void execute() {
        BankAccount account = facade.createBankAccount(name, initialBalance);
        System.out.println("Account created successfully with ID: " + account.getId());
    }
}
