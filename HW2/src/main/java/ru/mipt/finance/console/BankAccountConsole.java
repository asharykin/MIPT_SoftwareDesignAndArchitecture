package ru.mipt.finance.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.account.CreateBankAccountCommand;
import ru.mipt.finance.command.impl.account.DeleteBankAccountCommand;
import ru.mipt.finance.command.impl.account.ListBankAccountsCommand;
import ru.mipt.finance.command.impl.account.UpdateBankAccountCommand;
import ru.mipt.finance.facade.BankAccountFacade;

import java.math.BigDecimal;
import java.util.Scanner;

import static ru.mipt.finance.console.ConsoleUtils.executeCommand;
import static ru.mipt.finance.console.ConsoleUtils.readIntInput;

@Component
public class BankAccountConsole {
    private final BankAccountFacade bankAccountFacade;
    private final Scanner scanner;

    @Autowired
    public BankAccountConsole(BankAccountFacade bankAccountFacade, Scanner scanner) {
        this.bankAccountFacade = bankAccountFacade;
        this.scanner = scanner;
    }

    public void manageBankAccounts() {
        boolean managingAccounts = true;

        while (managingAccounts) {
            printMenu();

            int choice = readIntInput(scanner);

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

    private void printMenu() {
        System.out.println("\n===== BANK ACCOUNTS =====");
        System.out.println("1. List all accounts");
        System.out.println("2. Create an account");
        System.out.println("3. Update an account");
        System.out.println("4. Delete an account");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

    public void listAccounts(boolean trackTime) {
        ListBankAccountsCommand command = new ListBankAccountsCommand(bankAccountFacade);
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
        Integer id = readIntInput(scanner);

        System.out.print("Enter new account name: ");
        String name = scanner.nextLine();

        UpdateBankAccountCommand command = new UpdateBankAccountCommand(bankAccountFacade, id, name);
        executeCommand(command, true);
    }

    private void deleteAccount() {
        listAccounts(false);

        System.out.print("Enter account ID to delete: ");
        Integer id = readIntInput(scanner);

        DeleteBankAccountCommand command = new DeleteBankAccountCommand(bankAccountFacade, id);
        executeCommand(command, true);
    }
}
