package ru.mipt.finance.command.impl.category;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpdateCategoryCommand extends BaseCategoryCommand {
    private final Integer categoryId;
    private final String newName;

    public UpdateCategoryCommand(Integer categoryId, String newName) {
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
