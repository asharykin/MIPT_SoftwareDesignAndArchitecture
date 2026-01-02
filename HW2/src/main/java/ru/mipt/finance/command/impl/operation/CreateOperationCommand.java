package ru.mipt.finance.command.impl.operation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mipt.finance.model.Operation;
import ru.mipt.finance.model.OperationType;

import java.math.BigDecimal;

@Component
@Scope("prototype")
public class CreateOperationCommand extends BaseOperationCommand {
    private final OperationType type;
    private final Integer accountId;
    private final BigDecimal amount;
    private final String description;
    private final Integer categoryId;

    public CreateOperationCommand(OperationType type, Integer accountId, BigDecimal amount,
                                  String description, Integer categoryId) {
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
