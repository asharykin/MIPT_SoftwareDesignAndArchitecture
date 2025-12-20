package ru.mipt.finance.builder;

import ru.mipt.finance.model.Category;
import ru.mipt.finance.model.OperationType;

public class CategoryBuilder {
    private Integer id;
    private OperationType type;
    private String name;

    public CategoryBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder type(OperationType type) {
        this.type = type;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Category build() {
        return new Category(id, type, name);
    }
}
