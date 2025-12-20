package ru.mipt.finance.repository;

import org.springframework.stereotype.Repository;
import ru.mipt.finance.model.Operation;

import java.util.*;

@Repository
public class OperationRepository {
    private final Map<Integer, Operation> operations = new HashMap<>();

    public void save(Operation operation) {
        operations.put(operation.getId(), operation);
    }

    public Optional<Operation> findById(Integer id) {
        return Optional.ofNullable(operations.get(id));
    }

    public List<Operation> findAll() {
        return new ArrayList<>(operations.values());
    }

    public boolean deleteById(Integer id) {
        return operations.remove(id) != null;
    }

    public void deleteByBankAccountId(Integer bankAccountId) {
        operations.values().removeIf(operation -> operation.getBankAccountId().equals(bankAccountId));
    }

    public void deleteByCategoryId(Integer categoryId) {
        operations.values().removeIf(operation -> operation.getCategoryId().equals(categoryId));
    }
}
