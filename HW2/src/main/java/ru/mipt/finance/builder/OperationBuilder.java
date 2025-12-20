package ru.mipt.finance.builder;

import ru.mipt.finance.model.Operation;
import ru.mipt.finance.model.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperationBuilder {
    private Integer id;
    private OperationType type;
    private Integer bankAccountId;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private Integer categoryId;

    public OperationBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public OperationBuilder type(OperationType type) {
        this.type = type;
        return this;
    }

    public OperationBuilder bankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
        return this;
    }

    public OperationBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public OperationBuilder date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public OperationBuilder description(String description) {
        this.description = description;
        return this;
    }

    public OperationBuilder categoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Operation build() {
        return new Operation(id, type, bankAccountId, amount, date, description, categoryId);
    }
}
