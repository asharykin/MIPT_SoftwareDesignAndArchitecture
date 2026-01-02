package ru.mipt.finance.console;

import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.account.CreateBankAccountCommand;
import ru.mipt.finance.command.impl.account.DeleteBankAccountCommand;
import ru.mipt.finance.command.impl.account.ListBankAccountsCommand;
import ru.mipt.finance.command.impl.account.UpdateBankAccountCommand;

import java.math.BigDecimal;

@Component
public class BankAccountConsole extends BaseConsole {

    public void manageBankAccounts() {
        boolean managingAccounts = true;

        while (managingAccounts) {
            printMenu();

            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> listBankAccounts(true);
                    case 2 -> createBankAccount();
                    case 3 -> updateBankAccount();
                    case 4 -> deleteBankAccount();
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

    void listBankAccounts(boolean trackTime) {
        ListBankAccountsCommand command = context.getBean(ListBankAccountsCommand.class);
        executeCommand(command, trackTime);
    }

    private void createBankAccount() {
        System.out.print("Enter account name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance (default 0): ");
        String balanceStr = scanner.nextLine();
        BigDecimal balance = balanceStr.isEmpty() ? BigDecimal.ZERO : new BigDecimal(balanceStr);

        CreateBankAccountCommand command = context.getBean(CreateBankAccountCommand.class, name, balance);
        executeCommand(command, true);
    }

    private void updateBankAccount() {
        listBankAccounts(false);

        System.out.print("Enter ID of the account you want to update: ");
        Integer id = readIntInput();

        System.out.print("Enter new account name: ");
        String name = scanner.nextLine();

        UpdateBankAccountCommand command = context.getBean(UpdateBankAccountCommand.class, id, name);
        executeCommand(command, true);
    }

    private void deleteBankAccount() {
        listBankAccounts(false);

        System.out.print("Enter ID of the account you want to delete: ");
        Integer id = readIntInput();

        DeleteBankAccountCommand command = context.getBean(DeleteBankAccountCommand.class, id);
        executeCommand(command, true);
    }
}
