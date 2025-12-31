package ru.mipt.finance.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.facade.OperationFacade;

import java.io.*;
import java.util.List;
import java.util.Map;

@Component
public class YamlDataImporter extends DataImporter {
    private final Yaml yaml;

    @Autowired
    public YamlDataImporter(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade,
                            OperationFacade operationFacade, Yaml yaml) {
        super(bankAccountFacade, categoryFacade, operationFacade);
        this.yaml = yaml;
    }

    @Override
    protected List<Map<String, String>> parseAccounts(File dir) throws IOException {
        File accountsFile = new File(dir, "accounts.yaml");
        if (accountsFile.exists()) {
            return parseFile(accountsFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseCategories(File dir) throws IOException {
        File categoriesFile = new File(dir, "categories.yaml");
        if (categoriesFile.exists()) {
            return parseFile(categoriesFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseOperations(File dir) throws IOException {
        File operationsFile = new File(dir, "operations.yaml");
        if (operationsFile.exists()) {
            return parseFile(operationsFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseFile(File file) throws IOException {
        List<Map<String, String>> data;
        try (Reader inputStream = new FileReader(file)) {
            data = yaml.load(inputStream);
        }
        return data;
    }

    @Override
    public boolean supportsFormat(DataFormat format) {
        return format == DataFormat.YAML;
    }
}
