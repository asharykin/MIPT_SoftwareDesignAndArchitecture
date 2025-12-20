package ru.mipt.finance.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mipt.finance.command.Command;
import ru.mipt.finance.command.ExecutionTimeDecorator;
import ru.mipt.finance.command.impl.ImportDataCommand;
import ru.mipt.finance.command.impl.account.CreateBankAccountCommand;
import ru.mipt.finance.command.impl.account.DeleteBankAccountCommand;
import ru.mipt.finance.command.impl.account.ListAccountsCommand;
import ru.mipt.finance.command.impl.account.UpdateBankAccountCommand;
import ru.mipt.finance.command.impl.category.CreateCategoryCommand;
import ru.mipt.finance.command.impl.category.DeleteCategoryCommand;
import ru.mipt.finance.command.impl.category.ListCategoriesCommand;
import ru.mipt.finance.command.impl.category.UpdateCategoryCommand;
import ru.mipt.finance.command.impl.operation.CreateOperationCommand;
import ru.mipt.finance.command.impl.operation.DeleteOperationCommand;
import ru.mipt.finance.command.impl.operation.ListOperationsCommand;
import ru.mipt.finance.composite.ImporterComposite;
import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.facade.OperationFacade;
import ru.mipt.finance.importer.DataFormat;
import ru.mipt.finance.model.OperationType;

import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner {
    private final BankAccountFacade bankAccountFacade;
    private final CategoryFacade categoryFacade;
    private final OperationFacade operationFacade;
    private final ImporterComposite importerComposite;

    private final Scanner scanner;

    @Autowired
    public ConsoleApplication(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade,
                              OperationFacade operationFacade, ImporterComposite importerComposite, Scanner scanner) {
        this.bankAccountFacade = bankAccountFacade;
        this.categoryFacade = categoryFacade;
        this.operationFacade = operationFacade;
        this.importerComposite = importerComposite;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            printMainMenu();

            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> manageBankAccounts();
                    case 2 -> manageCategories();
                    case 3 -> manageOperations();
                    case 4 -> importData();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Manage Bank Accounts");
        System.out.println("2. Manage Categories");
        System.out.println("3. Manage Operations");
        System.out.println("4. Import Data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void manageBankAccounts() {
        boolean managingAccounts = true;

        while (managingAccounts) {
            System.out.println("\n===== BANK ACCOUNTS =====");
            System.out.println("1. List All Accounts");
            System.out.println("2. Create Account");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> listAccounts(true);
                    case 2 -> createAccount();
                    case 3 -> updateAccount();
                    case 4 -> deleteAccount();
                    case 0 -> managingAccounts = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private void listAccounts(boolean trackTime) {
        ListAccountsCommand command = new ListAccountsCommand(bankAccountFacade);
        executeCommand(command, trackTime);
    }

    private void createAccount() {
        System.out.print("Enter account name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance (default 0): ");
        String balanceStr = scanner.nextLine();
        BigDecimal balance = balanceStr.isEmpty() ? BigDecimal.ZERO : new BigDecimal(balanceStr);

        CreateBankAccountCommand command = new CreateBankAccountCommand(bankAccountFacade, name, balance);
        executeCommand(command, true);
    }

    private void updateAccount() {
        listAccounts(false);

        System.out.print("Enter account ID to update: ");
        Integer id = readIntInput();

        System.out.print("Enter new account name: ");
        String name = scanner.nextLine();

        UpdateBankAccountCommand command = new UpdateBankAccountCommand(bankAccountFacade, id, name);
        executeCommand(command, true);
    }

    private void deleteAccount() {
        listAccounts(false);

        System.out.print("Enter account ID to delete: ");
        Integer id = readIntInput();

        DeleteBankAccountCommand command = new DeleteBankAccountCommand(bankAccountFacade, id);
        executeCommand(command, true);
    }

    private void manageCategories() {
        boolean managingCategories = true;

        while (managingCategories) {
            System.out.println("\n===== CATEGORIES =====");
            System.out.println("1. List All Categories");
            System.out.println("2. Create Category");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> listCategories(true);
                    case 2 -> createCategory();
                    case 3 -> updateCategory();
                    case 4 -> deleteCategory();
                    case 0 -> managingCategories = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private void listCategories(boolean trackTime) {
        ListCategoriesCommand command = new ListCategoriesCommand(categoryFacade);
        executeCommand(command, trackTime);
    }

    private void createCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();

        System.out.println("Select category type: ");
        System.out.println("1. INCOME");
        System.out.println("2. EXPENSE");
        System.out.print("Enter your choice (1 or 2): ");

        int typeChoice = readIntInput();
        OperationType type;
        switch (typeChoice) {
            case 1 -> type = OperationType.INCOME;
            case 2 -> type = OperationType.EXPENSE;
            default -> {
                System.out.println("Invalid option. Please try again.");
                return;
            }
        }

        CreateCategoryCommand command = new CreateCategoryCommand(categoryFacade, name, type);
        executeCommand(command, true);
    }

    private void updateCategory() {
        listCategories(false);

        System.out.print("Enter category ID to update: ");
        Integer id = readIntInput();

        System.out.print("Enter new category name: ");
        String name = scanner.nextLine();

        UpdateCategoryCommand command = new UpdateCategoryCommand(categoryFacade, id, name);
        executeCommand(command, true);
    }

    private void deleteCategory() {
        listCategories(false);

        System.out.print("Enter category ID to delete: ");
        Integer id = readIntInput();

        DeleteCategoryCommand command = new DeleteCategoryCommand(categoryFacade, id);
        executeCommand(command,  true);
    }

    private void manageOperations() {
        boolean managingOperations = true;

        while (managingOperations) {
            System.out.println("\n===== OPERATIONS =====");
            System.out.println("1. List All Operations");
            System.out.println("2. Create Operation");
            System.out.println("3. Delete Operation");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

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

    private void listOperations(boolean trackTime) {
        ListOperationsCommand command = new ListOperationsCommand(operationFacade);
        executeCommand(command,  trackTime);
    }

    private void createOperation() {
        listAccounts(false);
        listCategories(false);

        System.out.println("\nSelect operation type: ");
        System.out.println("1. INCOME");
        System.out.println("2. EXPENSE");
        System.out.print("Enter your choice (1 or 2): ");

        int typeChoice = readIntInput();

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
        Integer accountId = readIntInput();

        System.out.print("Enter amount: ");
        String amountStr = scanner.nextLine();
        BigDecimal amount = new BigDecimal(amountStr);

        System.out.print("Enter category ID: ");
        Integer categoryId = readIntInput();

        System.out.print("Enter description (optional): ");
        String description = scanner.nextLine();

        CreateOperationCommand command = new CreateOperationCommand(operationFacade, type, accountId, amount, description, categoryId);
        executeCommand(command, true);
    }

    private void deleteOperation() {
        listOperations(false);

        System.out.print("Enter operation ID to delete: ");
        Integer id = readIntInput();

        DeleteOperationCommand command = new DeleteOperationCommand(operationFacade, id);
        executeCommand(command, true);
    }

    private void importData() {
        System.out.println("\n===== IMPORT DATA =====");
        System.out.println("Select import format:");
        System.out.println("1. JSON");
        System.out.println("2. YAML");
        System.out.println("3. CSV");
        System.out.print("Enter choice (1, 2 or 3): ");

        int formatChoice = readIntInput();
        DataFormat format;
        switch (formatChoice) {
            case 1 -> format = DataFormat.JSON;
            case 2 -> format = DataFormat.YAML;
            case 3 -> format = DataFormat.CSV;
            default -> {
                System.out.println("Invalid option. Please try again.");
                return;
            }
        }

        if (format == DataFormat.CSV) {
            System.out.print("Enter path to directory with input files: ");
        } else {
            System.out.print("Enter path to input file: ");
        }
        String filePath = scanner.nextLine();
        File file =  new File(filePath);

        ImportDataCommand command = new ImportDataCommand(importerComposite, format, file);
        executeCommand(command, true);
    }

    private int readIntInput() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void executeCommand(Command command, boolean trackTime) {
        if (trackTime) {
            ExecutionTimeDecorator wrappedCommand = new ExecutionTimeDecorator(command);
            wrappedCommand.execute();
        } else {
            command.execute();
        }
    }
}
