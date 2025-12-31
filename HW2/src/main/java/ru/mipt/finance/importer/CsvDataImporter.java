package ru.mipt.finance.importer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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
public class CsvDataImporter extends DataImporter {
    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;

    @Autowired
    public CsvDataImporter(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade,
                           OperationFacade operationFacade, CsvMapper csvMapper) {
        super(bankAccountFacade, categoryFacade, operationFacade);
        this.csvMapper = csvMapper;
        this.csvSchema = csvMapper.schemaWithHeader();
    }

    @Override
    protected List<Map<String, String>> parseAccounts(File dir) throws IOException {
        File accountsFile = new File(dir, "accounts.csv");
        if (accountsFile.exists()) {
            return parseFile(accountsFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseCategories(File dir) throws IOException {
        File categoriesFile = new File(dir, "categories.csv");
        if (categoriesFile.exists()) {
            return parseFile(categoriesFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseOperations(File dir) throws IOException {
        File operationsFile = new File(dir, "operations.csv");
        if (operationsFile.exists()) {
            return parseFile(operationsFile);
        }
        return List.of();
    }

    @Override
    protected List<Map<String, String>> parseFile(File file) throws IOException {
        JsonParser parser = csvMapper.createParser(file);
        parser.setSchema(csvSchema);
        return csvMapper.readValues(parser, new TypeReference<Map<String, String>>() {}).readAll();
    }

    @Override
    public boolean supportsFormat(DataFormat format) {
        return format == DataFormat.CSV;
    }
}
