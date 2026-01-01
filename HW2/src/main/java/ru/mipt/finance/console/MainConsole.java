package ru.mipt.finance.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static ru.mipt.finance.console.ConsoleUtils.readIntInput;

@Component
public class MainConsole implements CommandLineRunner {
    private final BankAccountConsole bankAccountConsole;
    private final CategoryConsole categoryConsole;
    private final OperationConsole operationConsole;
    private final ImportConsole importConsole;
    private final Scanner scanner;

    @Autowired
    public MainConsole(BankAccountConsole bankAccountConsole, CategoryConsole categoryConsole,
                       OperationConsole operationConsole, ImportConsole importConsole, Scanner scanner) {
        this.bankAccountConsole = bankAccountConsole;
        this.categoryConsole = categoryConsole;
        this.operationConsole = operationConsole;
        this.importConsole = importConsole;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            printMainMenu();

            int choice = readIntInput(scanner);

            try {
                switch (choice) {
                    case 1 -> bankAccountConsole.manageBankAccounts();
                    case 2 -> categoryConsole.manageCategories();
                    case 3 -> operationConsole.manageOperations();
                    case 4 -> importConsole.importData();
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
        System.out.println("1. Manage bank accounts");
        System.out.println("2. Manage categories");
        System.out.println("3. Manage operations");
        System.out.println("4. Import data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
}
