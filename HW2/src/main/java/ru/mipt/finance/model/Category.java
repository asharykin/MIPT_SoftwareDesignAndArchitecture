package ru.mipt.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.finance.builder.CategoryBuilder;

@Getter
@Setter
@AllArgsConstructor
public class Category {
    private Integer id;
    private OperationType type;
    private String name;

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }
}
