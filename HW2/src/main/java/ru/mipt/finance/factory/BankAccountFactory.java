package ru.mipt.finance.factory;

import org.springframework.stereotype.Component;
import ru.mipt.finance.model.BankAccount;

import java.math.BigDecimal;

@Component
public class BankAccountFactory {
    private Integer counter = 0;

    public BankAccount createBankAccount(String name, BigDecimal initialBalance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Bank account name cannot be empty");
        }
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        return BankAccount.builder()
                .id(++counter)
                .name(name)
                .balance(initialBalance)
                .build();
    }
}
