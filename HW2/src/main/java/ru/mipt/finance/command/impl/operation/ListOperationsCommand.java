package ru.mipt.finance.command.impl.operation;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.OperationFacade;
import ru.mipt.finance.model.Operation;

import java.util.List;

public class ListOperationsCommand implements Command {
    private final OperationFacade facade;

    public ListOperationsCommand(OperationFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        List<Operation> operations = facade.getAllOperations();

        if (operations.isEmpty()) {
            System.out.println("No operations found.");
            return;
        }

        System.out.println("\nOperations:");
        for (Operation operation : operations) {
            System.out.println(operation.getId() + " | " + operation.getType() +
                    " | Amount: " + operation.getAmount() +
                    " | Date: " + operation.getDate() +
                    " | Description: " + operation.getDescription());
        }
    }
}
