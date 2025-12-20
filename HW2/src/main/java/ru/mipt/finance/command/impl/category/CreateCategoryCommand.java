package ru.mipt.finance.command.impl.category;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.model.Category;
import ru.mipt.finance.model.OperationType;

public class CreateCategoryCommand implements Command {
    private final CategoryFacade facade;

    private final String name;
    private final OperationType type;

    public CreateCategoryCommand(CategoryFacade facade, String name, OperationType type) {
        this.facade = facade;
        this.name = name;
        this.type = type;
    }

    @Override
    public void execute() {
        Category category = facade.createCategory(name, type);
        System.out.println("Category created successfully with ID: " + category.getId());
    }
}
