package ru.mipt.finance.command.impl.account;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.BankAccountFacade;

public abstract class BaseBankAccountCommand implements Command {
    protected BankAccountFacade facade;

    @Autowired
    private void setFacade(BankAccountFacade facade) {
        this.facade = facade;
    }
}
