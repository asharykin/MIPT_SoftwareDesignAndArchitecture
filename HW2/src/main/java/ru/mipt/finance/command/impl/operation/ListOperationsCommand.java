package ru.mipt.finance.command.impl.operation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mipt.finance.model.Operation;

import java.util.List;

@Component
@Scope("prototype")
public class ListOperationsCommand extends BaseOperationCommand {

    @Override
    public void execute() {
        List<Operation> operations = facade.getAllOperations();

        if (operations.isEmpty()) {
            System.out.println("No operations found.");
            return;
        }

        System.out.println("\nOperations:");
        for (Operation operation : operations) {
            System.out.println(operation);
        }
    }
}
