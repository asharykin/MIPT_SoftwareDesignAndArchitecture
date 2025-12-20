package ru.mipt.finance.repository;

import org.springframework.stereotype.Repository;
import ru.mipt.finance.model.BankAccount;

import java.util.*;

@Repository
public class BankAccountRepository {
    private final Map<Integer, BankAccount> bankAccounts = new HashMap<>();

    public void save(BankAccount account) {
        bankAccounts.put(account.getId(), account);
    }

    public Optional<BankAccount> findById(Integer id) {
        return Optional.ofNullable(bankAccounts.get(id));
    }

    public List<BankAccount> findAll() {
        return new ArrayList<>(bankAccounts.values());
    }

    public boolean deleteById(Integer id) {
        return bankAccounts.remove(id) != null;
    }
}
