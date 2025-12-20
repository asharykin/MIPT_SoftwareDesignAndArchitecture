package ru.mipt.finance.command.impl.operation;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.OperationFacade;
import ru.mipt.finance.model.Operation;
import ru.mipt.finance.model.OperationType;

import java.math.BigDecimal;

public class CreateOperationCommand implements Command {
    private final OperationFacade facade;

    private final OperationType type;
    private final Integer accountId;
    private final BigDecimal amount;
    private final String description;
    private final Integer categoryId;

    public CreateOperationCommand(OperationFacade facade, OperationType type, Integer accountId,
                                  BigDecimal amount, String description, Integer categoryId) {
        this.facade = facade;
        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public void execute() {
        Operation operation = facade.createOperation(type, accountId, amount, categoryId, description);
        System.out.println("Operation created successfully with ID: " + operation.getId());
    }
}
