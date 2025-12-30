package ru.mipt.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.finance.builder.BankAccountBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BankAccount {
    private Integer id;
    private String name;
    private BigDecimal balance;

    public static BankAccountBuilder builder() {
        return new BankAccountBuilder();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
