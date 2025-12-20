package ru.mipt.finance.factory;

import org.springframework.stereotype.Component;
import ru.mipt.finance.model.Operation;
import ru.mipt.finance.model.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class OperationFactory {
    private Integer counter = 0;

    public Operation createOperation(OperationType type, Integer bankAccountId, BigDecimal amount,
                                     Integer categoryId, String description) {
        if (type == null) {
            throw new IllegalArgumentException("Operation type cannot be null");
        }
        if (bankAccountId == null) {
            throw new IllegalArgumentException("Bank account ID cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        return Operation.builder()
                .id(++counter)
                .type(type)
                .bankAccountId(bankAccountId)
                .amount(amount)
                .date(LocalDateTime.now())
                .categoryId(categoryId)
                .description(description)
                .build();
    }
}
