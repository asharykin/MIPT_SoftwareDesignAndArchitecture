package ru.mipt.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mipt.finance.console.*;

@SpringBootApplication
public class ApplicationConsole extends BaseConsole implements CommandLineRunner {
    private final BankAccountConsole bankAccountConsole;
    private final CategoryConsole categoryConsole;
    private final OperationConsole operationConsole;
    private final ImportConsole importConsole;

    @Autowired
    public ApplicationConsole(BankAccountConsole bankAccountConsole, CategoryConsole categoryConsole,
                              OperationConsole operationConsole, ImportConsole importConsole) {
        this.bankAccountConsole = bankAccountConsole;
        this.categoryConsole = categoryConsole;
        this.operationConsole = operationConsole;
        this.importConsole = importConsole;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsole.class, args);
    }

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            printMainMenu();

            int choice = readIntInput();

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
