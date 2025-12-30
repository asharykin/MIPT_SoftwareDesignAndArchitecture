package ru.mipt.finance.command.impl.category;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.model.Category;

import java.util.List;

public class ListCategoriesCommand implements Command {
    private final CategoryFacade facade;

    public ListCategoriesCommand(CategoryFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        List<Category> categories = facade.getAllCategories();

        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("\nCategories:");
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}
