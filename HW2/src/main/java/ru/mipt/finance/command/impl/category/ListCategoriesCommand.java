package ru.mipt.finance.command.impl.category;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mipt.finance.model.Category;

import java.util.List;

@Component
@Scope("prototype")
public class ListCategoriesCommand extends BaseCategoryCommand {

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
