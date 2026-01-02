package ru.mipt.finance.command.impl.operation;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.OperationFacade;

public class DeleteOperationCommand extends BaseOperationCommand {
    private final Integer operationId;

    public DeleteOperationCommand(Integer operationId) {
        this.operationId = operationId;
    }

    @Override
    public void execute() {
        boolean deleted = facade.deleteOperation(operationId);
        if (deleted) {
            System.out.println("Operation deleted successfully.");
        } else {
            System.out.println("Operation not found or could not be deleted.");
        }
    }
}
