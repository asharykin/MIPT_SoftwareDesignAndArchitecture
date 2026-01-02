package ru.mipt.finance.command.impl.category;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.finance.command.Command;
import ru.mipt.finance.facade.CategoryFacade;

public abstract class BaseCategoryCommand implements Command {
    protected CategoryFacade facade;

    @Autowired
    public void setFacade(CategoryFacade facade) {
        this.facade = facade;
    }
}
