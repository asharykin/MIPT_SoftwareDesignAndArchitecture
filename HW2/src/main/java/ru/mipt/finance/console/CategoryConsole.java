package ru.mipt.finance.console;

import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.category.CreateCategoryCommand;
import ru.mipt.finance.command.impl.category.DeleteCategoryCommand;
import ru.mipt.finance.command.impl.category.ListCategoriesCommand;
import ru.mipt.finance.command.impl.category.UpdateCategoryCommand;
import ru.mipt.finance.model.OperationType;

@Component
public class CategoryConsole extends BaseConsole {

    public void manageCategories() {
        boolean managingCategories = true;

        while (managingCategories) {
            printMenu();

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

    private void printMenu() {
        System.out.println("\n===== CATEGORIES =====");
        System.out.println("1. List all categories");
        System.out.println("2. Create a category");
        System.out.println("3. Update a category");
        System.out.println("4. Delete a category");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

    void listCategories(boolean trackTime) {
        ListCategoriesCommand command = context.getBean(ListCategoriesCommand.class);
        executeCommand(command, trackTime);
    }

    private void createCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();

        printCategoryTypes();

        int choice = readIntInput();

        OperationType type = getOperationType(choice);

        if (type != null) {
            CreateCategoryCommand command = context.getBean(CreateCategoryCommand.class, name, type);
            executeCommand(command, true);
        }
    }

    private void printCategoryTypes() {
        System.out.println("Select category type: ");
        System.out.println("1. INCOME");
        System.out.println("2. EXPENSE");
        System.out.print("Enter your choice (1 or 2): ");
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

    private void updateCategory() {
        listCategories(false);

        System.out.print("Enter ID of the category you want to update: ");
        Integer id = readIntInput();

        System.out.print("Enter new category name: ");
        String name = scanner.nextLine();

        UpdateCategoryCommand command = context.getBean(UpdateCategoryCommand.class, id, name);
        executeCommand(command, true);
    }

    private void deleteCategory() {
        listCategories(false);

        System.out.print("Enter ID of the category you want to update: ");
        Integer id = readIntInput();

        DeleteCategoryCommand command = context.getBean(DeleteCategoryCommand.class, id);
        executeCommand(command,  true);
    }
}
