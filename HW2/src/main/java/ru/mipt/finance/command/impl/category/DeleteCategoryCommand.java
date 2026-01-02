package ru.mipt.finance.command.impl.category;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DeleteCategoryCommand extends BaseCategoryCommand {
    private final Integer categoryId;

    public DeleteCategoryCommand(Integer categoryId) {
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
