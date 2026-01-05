package ru.mipt.finance.cli;

import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.operation.CreateOperationCommand;
import ru.mipt.finance.command.impl.operation.DeleteOperationCommand;
import ru.mipt.finance.command.impl.operation.ListOperationsCommand;
import ru.mipt.finance.model.OperationType;

import java.math.BigDecimal;

@Component
public class OperationCli extends BaseCli {
    private final BankAccountCli bankAccountCli;
    private final CategoryCli categoryConsole;

    public OperationCli(BankAccountCli bankAccountCli, CategoryCli categoryConsole) {
        this.bankAccountCli = bankAccountCli;
        this.categoryConsole = categoryConsole;
    }

    public void manageOperations() {
        boolean managingOperations = true;

        while (managingOperations) {
            printMenu();

            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> listOperations(true);
                    case 2 -> createOperation();
                    case 3 -> deleteOperation();
                    case 0 -> managingOperations = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== OPERATIONS =====");
        System.out.println("1. List all operations");
        System.out.println("2. Create an operation");
        System.out.println("3. Delete an operation");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }


    void listOperations(boolean trackTime) {
        ListOperationsCommand command = context.getBean(ListOperationsCommand.class);
        executeCommand(command, trackTime);
    }

    private void createOperation() {
        bankAccountCli.listBankAccounts(false);
        categoryConsole.listCategories(false);

        printOperationTypes();

        int choice = readIntInput();

        OperationType type = getOperationType(choice);
        if (type == null) {
            return;
        }

        System.out.print("Enter bank account ID: ");
        Integer accountId = readIntInput();

        System.out.print("Enter amount: ");
        String amountStr = scanner.nextLine();
        BigDecimal amount = new BigDecimal(amountStr);

        System.out.print("Enter category ID: ");
        Integer categoryId = readIntInput();

        System.out.print("Enter description (optional): ");
        String description = scanner.nextLine();

        CreateOperationCommand command = context.getBean(CreateOperationCommand.class, type, accountId, amount, description, categoryId);
        executeCommand(command, true);
    }

    private OperationType getOperationType(int choice) {
        switch (choice) {
            case 1 -> {
                return OperationType.INCOME;
            }
            case 2 -> {
                return OperationType.EXPENSE;
            }
            default -> {
                System.out.println("Invalid option. Please try again.");
                return null;
            }
        }
    }

    private void printOperationTypes() {
        System.out.println("\nSelect operation type: ");
        System.out.println("1. INCOME");
        System.out.println("2. EXPENSE");
        System.out.print("Enter your choice (1 or 2): ");
    }

    private void deleteOperation() {
        listOperations(false);

        System.out.print("Enter operation ID to delete: ");
        Integer id = readIntInput();

        DeleteOperationCommand command = context.getBean(DeleteOperationCommand.class, id);
        executeCommand(command, true);
    }
}
