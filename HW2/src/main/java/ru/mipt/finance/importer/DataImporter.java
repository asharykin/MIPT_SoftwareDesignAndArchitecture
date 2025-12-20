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

    public final void importData(File file) throws IOException {
        if (!file.exists() || !file.canRead()) {
            throw new IOException("Cannot read file: " + file.getAbsolutePath());
        }
        Map<String, Object> data = parseFile(file);
        processAccounts(data);
        processCategories(data);
        processOperations(data);
    }


    protected abstract Map<String, Object> parseFile(File file) throws IOException;

    private void processAccounts(Map<String, Object> data) {
        if (data.containsKey("accounts")) {
            List<Map<String, String>> accounts = (List<Map<String, String>>) data.get("accounts");
            for (Map<String, String> accountData : accounts) {
                String name = accountData.get("name");
                BigDecimal balance = new BigDecimal(accountData.get("balance"));
                bankAccountFacade.createBankAccount(name, balance);
            }
        }
    }

    private void processCategories(Map<String, Object> data) {
        if (data.containsKey("categories")) {
            List<Map<String, String>> categories = (List<Map<String, String>>) data.get("categories");
            for (Map<String, String> categoryData : categories) {
                String name = categoryData.get("name");
                OperationType type = OperationType.valueOf(categoryData.get("type"));
                categoryFacade.createCategory(name, type);
            }
        }
    }

    private void processOperations(Map<String, Object> data) {
        if (data.containsKey("operations")) {
            List<Map<String, String>> operations = (List<Map<String, String>>) data.get("operations");
            for (Map<String, String> operationData : operations) {
                OperationType type = OperationType.valueOf(operationData.get("type"));
                BigDecimal amount = new BigDecimal(operationData.get("amount"));
                String description = operationData.get("description");
                Integer accountId = Integer.parseInt(operationData.get("account_id"));
                Integer categoryId = Integer.parseInt(operationData.get("category_id"));
                operationFacade.createOperation(type, accountId, amount, categoryId, description);
            }
        }
    }

    public abstract boolean supportsFormat(DataFormat format);
}
