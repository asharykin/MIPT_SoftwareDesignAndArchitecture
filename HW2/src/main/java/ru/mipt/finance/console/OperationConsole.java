package ru.mipt.finance.console;

import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.operation.CreateOperationCommand;
import ru.mipt.finance.command.impl.operation.DeleteOperationCommand;
import ru.mipt.finance.command.impl.operation.ListOperationsCommand;
import ru.mipt.finance.facade.OperationFacade;
import ru.mipt.finance.model.OperationType;

import java.math.BigDecimal;
import java.util.Scanner;

import static ru.mipt.finance.console.ConsoleUtils.executeCommand;
import static ru.mipt.finance.console.ConsoleUtils.readIntInput;

@Component
public class OperationConsole {
    private final OperationFacade operationFacade;
    private final BankAccountConsole bankAccountConsole;
    private final CategoryConsole categoryConsole;
    private final Scanner scanner;

    public OperationConsole(OperationFacade operationFacade, BankAccountConsole bankAccountConsole, CategoryConsole categoryConsole, Scanner scanner) {
        this.operationFacade = operationFacade;
        this.bankAccountConsole = bankAccountConsole;
        this.categoryConsole = categoryConsole;
        this.scanner = scanner;
    }

    public void manageOperations() {
        boolean managingOperations = true;

        while (managingOperations) {
            printMenu();

            int choice = readIntInput(scanner);

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


    public void listOperations(boolean trackTime) {
        ListOperationsCommand command = new ListOperationsCommand(operationFacade);
        executeCommand(command, trackTime);
    }

    private void createOperation() {
        bankAccountConsole.listAccounts(false);
        categoryConsole.listCategories(false);

        printOperationTypes();

        int typeChoice = readIntInput(scanner);

        OperationType type;
        switch (typeChoice) {
            case 1 -> type = OperationType.INCOME;
            case 2 -> type = OperationType.EXPENSE;
            default -> {
                System.out.println("Invalid option. Please try again.");
                return;
            }
        }

        System.out.print("Enter bank account ID: ");
        Integer accountId = readIntInput(scanner);

        System.out.print("Enter amount: ");
        String amountStr = scanner.nextLine();
        BigDecimal amount = new BigDecimal(amountStr);

        System.out.print("Enter category ID: ");
        Integer categoryId = readIntInput(scanner);

        System.out.print("Enter description (optional): ");
        String description = scanner.nextLine();

        CreateOperationCommand command = new CreateOperationCommand(operationFacade, type, accountId, amount, description, categoryId);
        executeCommand(command, true);
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
        Integer id = readIntInput(scanner);

        DeleteOperationCommand command = new DeleteOperationCommand(operationFacade, id);
        executeCommand(command, true);
    }
}
