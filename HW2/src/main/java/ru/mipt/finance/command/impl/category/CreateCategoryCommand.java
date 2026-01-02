package ru.mipt.finance.command.impl.category;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mipt.finance.model.Category;
import ru.mipt.finance.model.OperationType;

@Component
@Scope("prototype")
public class CreateCategoryCommand extends BaseCategoryCommand {
    private final String name;
    private final OperationType type;

    public CreateCategoryCommand(String name, OperationType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void execute() {
        Category category = facade.createCategory(name, type);
        System.out.println("Category created successfully with ID: " + category.getId());
    }
}
