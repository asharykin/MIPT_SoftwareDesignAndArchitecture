package ru.mipt.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.finance.builder.OperationBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Operation {
    private Integer id;
    private OperationType type;
    private Integer bankAccountId;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private Integer categoryId;

    public static OperationBuilder builder() {
        return new OperationBuilder();
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", type=" + type +
                ", bankAccountId=" + bankAccountId +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
