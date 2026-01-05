package ru.mipt.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mipt.finance.cli.*;

@SpringBootApplication
public class ApplicationCli extends BaseCli implements CommandLineRunner {
    private final BankAccountCli bankAccountCli;
    private final CategoryCli categoryConsole;
    private final OperationCli operationConsole;
    private final ImportCli importConsole;

    @Autowired
    public ApplicationCli(BankAccountCli bankAccountCli, CategoryCli categoryConsole,
                          OperationCli operationConsole, ImportCli importConsole) {
        this.bankAccountCli = bankAccountCli;
        this.categoryConsole = categoryConsole;
        this.operationConsole = operationConsole;
        this.importConsole = importConsole;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCli.class, args);
    }

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            printMainMenu();

            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> bankAccountCli.manageBankAccounts();
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
