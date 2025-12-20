package ru.mipt.finance.factory;

import org.springframework.stereotype.Component;
import ru.mipt.finance.model.Category;
import ru.mipt.finance.model.OperationType;

@Component
public class CategoryFactory {
    private Integer counter = 0;

    public Category createCategory(String name, OperationType type) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Category type cannot be null");
        }
        return Category.builder()
                .id(++counter)
                .name(name)
                .type(type)
                .build();
    }
}
