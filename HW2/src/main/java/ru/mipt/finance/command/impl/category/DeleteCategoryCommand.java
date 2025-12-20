package ru.mipt.finance.command.impl.category;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.CategoryFacade;

public class DeleteCategoryCommand implements Command {
    private final CategoryFacade facade;

    private final Integer categoryId;

    public DeleteCategoryCommand(CategoryFacade facade, Integer categoryId) {
        this.facade = facade;
        this.categoryId = categoryId;
    }

    @Override
    public void execute() {
        boolean deleted = facade.deleteCategory(categoryId);
        if (deleted) {
            System.out.println("Category deleted successfully.");
        } else {
            System.out.println("Category not found or could not be deleted.");
        }
    }
}
