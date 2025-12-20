package ru.mipt.finance.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.finance.factory.OperationFactory;
import ru.mipt.finance.model.BankAccount;
import ru.mipt.finance.model.Category;
import ru.mipt.finance.model.Operation;
import ru.mipt.finance.model.OperationType;
import ru.mipt.finance.repository.BankAccountRepository;
import ru.mipt.finance.repository.CategoryRepository;
import ru.mipt.finance.repository.OperationRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OperationFacade {
    private final OperationFactory operationFactory;
    private final OperationRepository operationRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public OperationFacade(OperationFactory operationFactory, OperationRepository operationRepository,
                           BankAccountRepository bankAccountRepository, CategoryRepository categoryRepository) {
        this.operationFactory = operationFactory;
        this.operationRepository = operationRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.categoryRepository = categoryRepository;
    }

    public Operation createOperation(OperationType type, Integer bankAccountId, BigDecimal amount,
                                     Integer categoryId, String description) {
        BankAccount account = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found with ID: " + bankAccountId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));

        if (category.getType() != type) {
            throw new IllegalArgumentException("Category type " + category.getType() + " does not match operation type " + type);
        }

        Operation operation = operationFactory.createOperation(type, bankAccountId, amount, categoryId, description);

        if (type == OperationType.INCOME) {
            account.setBalance(account.getBalance().add(amount));
        } else {
            BigDecimal newBalance = account.getBalance().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalStateException("Operation would cause account balance to become negative");
            }
            account.setBalance(newBalance);
        }

        operationRepository.save(operation);
        bankAccountRepository.save(account);
        return operation;
    }

    public boolean deleteOperation(Integer operationId) {
        Optional<Operation> operationOpt = operationRepository.findById(operationId);
        if (operationOpt.isEmpty()) {
            return false;
        }

        Operation operation = operationOpt.get();
        BankAccount account = bankAccountRepository.findById(operation.getBankAccountId())
                .orElseThrow(() -> new IllegalStateException("Bank account not found for operation: " + operationId));

        if (operation.getType() == OperationType.INCOME) {
            account.setBalance(account.getBalance().subtract(operation.getAmount()));
        } else {
            account.setBalance(account.getBalance().add(operation.getAmount()));
        }

        boolean deleted = operationRepository.deleteById(operationId);
        bankAccountRepository.save(account);
        return deleted;
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }
}
