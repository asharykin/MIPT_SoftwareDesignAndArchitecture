package ru.mipt.finance.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.finance.factory.BankAccountFactory;
import ru.mipt.finance.model.BankAccount;
import ru.mipt.finance.repository.BankAccountRepository;
import ru.mipt.finance.repository.OperationRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountFacade {
    private final BankAccountFactory bankAccountFactory;
    private final BankAccountRepository bankAccountRepository;
    private final OperationRepository operationRepository;

    @Autowired
    public BankAccountFacade(BankAccountFactory bankAccountFactory, BankAccountRepository bankAccountRepository,
                             OperationRepository operationRepository) {
        this.bankAccountFactory = bankAccountFactory;
        this.bankAccountRepository = bankAccountRepository;
        this.operationRepository = operationRepository;
    }

    public BankAccount createBankAccount(String name, BigDecimal initialBalance) {
        BankAccount account = bankAccountFactory.createBankAccount(name, initialBalance);
        bankAccountRepository.save(account);
        return account;
    }

    public boolean updateBankAccount(Integer accountId, String newName) {
        Optional<BankAccount> accountOpt = bankAccountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            return false;
        }
        BankAccount account = accountOpt.get();
        account.setName(newName);
        bankAccountRepository.save(account);
        return true;
    }

    public boolean deleteBankAccount(Integer accountId) {
        if (bankAccountRepository.findById(accountId).isEmpty()) {
            return false;
        }
        operationRepository.deleteByBankAccountId(accountId);
        return bankAccountRepository.deleteById(accountId);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
}
