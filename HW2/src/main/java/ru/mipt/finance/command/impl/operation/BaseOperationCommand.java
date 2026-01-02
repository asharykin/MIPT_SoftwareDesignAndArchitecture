package ru.mipt.finance.command.impl.operation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.OperationFacade;

public abstract class BaseOperationCommand implements Command {
    protected OperationFacade facade;

    @Autowired
    public void setFacade(OperationFacade facade) {
        this.facade = facade;
    }
}
