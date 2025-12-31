package ru.mipt.finance.importer;

import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.facade.OperationFacade;
import ru.mipt.finance.model.OperationType;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public abstract class DataImporter {
    protected final BankAccountFacade bankAccountFacade;
    protected final CategoryFacade categoryFacade;
    protected final OperationFacade operationFacade;

    public DataImporter(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade, OperationFacade operationFacade) {
        this.bankAccountFacade = bankAccountFacade;
        this.categoryFacade = categoryFacade;
        this.operationFacade = operationFacade;
    }

    public final void importData(File dir) throws IOException {
        validateDirectory(dir);
        List<Map<String, String>> accounts = parseAccounts(dir);
        List<Map<String, String>> categories = parseCategories(dir);
        List<Map<String, String>> operations = parseOperations(dir);
        processAccounts(accounts);
        processCategories(categories);
        processOperations(operations);
    }

    protected abstract List<Map<String, String>> parseAccounts(File dir) throws IOException;

    protected abstract List<Map<String, String>> parseCategories(File dir) throws IOException;

    protected abstract List<Map<String, String>> parseOperations(File dir) throws IOException;

    private void validateDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IOException("Cannot find directory " + dir.getAbsolutePath());
        }
        if (!dir.isDirectory()) {
            throw new IOException(dir.getAbsolutePath() + " is not a directory");
        }
    }

    private void processAccounts(List<Map<String, String>> accounts) {
        for (Map<String, String> accountData : accounts) {
            String name = accountData.get("name");
            BigDecimal balance = new BigDecimal(accountData.get("balance"));
            bankAccountFacade.createBankAccount(name, balance);
        }
    }

    private void processCategories(List<Map<String, String>> categories) {
        for (Map<String, String> categoryData : categories) {
            String name = categoryData.get("name");
            OperationType type = OperationType.valueOf(categoryData.get("type"));
            categoryFacade.createCategory(name, type);
        }
    }

    private void processOperations(List<Map<String, String>> operations) {
        for (Map<String, String> operationData : operations) {
            OperationType type = OperationType.valueOf(operationData.get("type"));
            BigDecimal amount = new BigDecimal(operationData.get("amount"));
            String description = operationData.get("description");
            Integer accountId = Integer.parseInt(operationData.get("account_id"));
            Integer categoryId = Integer.parseInt(operationData.get("category_id"));
            operationFacade.createOperation(type, accountId, amount, categoryId, description);
        }
    }

    protected abstract List<Map<String, String>> parseFile(File file) throws IOException;

    public abstract boolean supportsFormat(DataFormat format);
}
