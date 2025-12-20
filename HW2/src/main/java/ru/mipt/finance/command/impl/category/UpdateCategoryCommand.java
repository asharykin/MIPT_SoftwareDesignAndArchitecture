package ru.mipt.finance.command.impl.category;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.CategoryFacade;

public class UpdateCategoryCommand implements Command {
    private final CategoryFacade facade;
    private final Integer categoryId;
    private final String newName;

    public UpdateCategoryCommand(CategoryFacade facade, Integer categoryId, String newName) {
        this.facade = facade;
        this.categoryId = categoryId;
        this.newName = newName;
    }

    @Override
    public void execute() {
        boolean updated = facade.updateCategory(categoryId, newName);
        if (updated) {
            System.out.println("Category updated successfully.");
        } else {
            System.out.println("Category not found or could not be updated.");
        }
    }
}
