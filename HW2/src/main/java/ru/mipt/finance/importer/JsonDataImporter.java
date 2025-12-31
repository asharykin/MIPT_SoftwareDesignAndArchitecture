package ru.mipt.finance.importer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.facade.OperationFacade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JsonDataImporter extends DataImporter {
    private final JsonMapper jsonMapper;

    @Autowired
    public JsonDataImporter(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade,
                            OperationFacade operationFacade, JsonMapper jsonMapper) {
        super(bankAccountFacade, categoryFacade, operationFacade);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected List<Map<String, String>> parseAccounts(File dir) throws IOException {
        File accountsFile = new File(dir, "accounts.json");
        if (accountsFile.exists()) {
            return parseFile(accountsFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseCategories(File dir) throws IOException {
        File categoriesFile = new File(dir, "categories.json");
        if (categoriesFile.exists()) {
            return parseFile(categoriesFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseOperations(File dir) throws IOException {
        File accountsFile = new File(dir, "operations.json");
        if (accountsFile.exists()) {
            return parseFile(accountsFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseFile(File file) throws IOException {
        return jsonMapper.readValue(file, new TypeReference<List<Map<String, String>>>() {});
    }

    @Override
    public boolean supportsFormat(DataFormat format) {
        return format == DataFormat.JSON;
    }
}
