package ru.mipt.finance.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.category.CreateCategoryCommand;
import ru.mipt.finance.command.impl.category.DeleteCategoryCommand;
import ru.mipt.finance.command.impl.category.ListCategoriesCommand;
import ru.mipt.finance.command.impl.category.UpdateCategoryCommand;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.model.OperationType;

import java.util.Scanner;

import static ru.mipt.finance.console.ConsoleUtils.executeCommand;
import static ru.mipt.finance.console.ConsoleUtils.readIntInput;

@Component
public class CategoryConsole {
    private final CategoryFacade categoryFacade;
    private final Scanner scanner;

    @Autowired
    public CategoryConsole(CategoryFacade categoryFacade, Scanner scanner) {
        this.categoryFacade = categoryFacade;
        this.scanner = scanner;
    }

    public void manageCategories() {
        boolean managingCategories = true;

        while (managingCategories) {
            printMenu();

            int choice = readIntInput(scanner);

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

    private void printMenu() {
        System.out.println("\n===== CATEGORIES =====");
        System.out.println("1. List all categories");
        System.out.println("2. Create a category");
        System.out.println("3. Update a category");
        System.out.println("4. Delete a category");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

    public void listCategories(boolean trackTime) {
        ListCategoriesCommand command = new ListCategoriesCommand(categoryFacade);
        executeCommand(command, trackTime);
    }

    private void createCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();

        printCategoryTypes();

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

        CreateCategoryCommand command = new CreateCategoryCommand(categoryFacade, name, type);
        executeCommand(command, true);
    }

    private void printCategoryTypes() {
        System.out.println("Select category type: ");
        System.out.println("1. INCOME");
        System.out.println("2. EXPENSE");
        System.out.print("Enter your choice (1 or 2): ");
    }

    private void updateCategory() {
        listCategories(false);

        System.out.print("Enter category ID to update: ");
        Integer id = readIntInput(scanner);

        System.out.print("Enter new category name: ");
        String name = scanner.nextLine();

        UpdateCategoryCommand command = new UpdateCategoryCommand(categoryFacade, id, name);
        executeCommand(command, true);
    }

    private void deleteCategory() {
        listCategories(false);

        System.out.print("Enter category ID to delete: ");
        Integer id = readIntInput(scanner);

        DeleteCategoryCommand command = new DeleteCategoryCommand(categoryFacade, id);
        executeCommand(command,  true);
    }
}
