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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvDataImporter extends DataImporter {
    private final CsvMapper csvMapper;

    @Autowired
    public CsvDataImporter(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade,
                           OperationFacade operationFacade, CsvMapper csvMapper) {
        super(bankAccountFacade, categoryFacade, operationFacade);
        this.csvMapper = csvMapper;
    }

    @Override
    protected Map<String, Object> parseFile(File file) throws IOException {
        Map<String, Object> result = new HashMap<>();

        File accountsFile = new File(file, "accounts.csv");
        if (accountsFile.exists()) {
            result.put("accounts", readCsvFile(accountsFile));
        }

        File categoriesFile = new File(file, "categories.csv");
        if (categoriesFile.exists()) {
            result.put("categories", readCsvFile(categoriesFile));
        }

        File operationsFile = new File(file, "operations.csv");
        if (operationsFile.exists()) {
            result.put("operations", readCsvFile(operationsFile));
        }

        return result;
    }

    private List<Map<String, String>> readCsvFile(File file) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        JsonParser parser = csvMapper.createParser(file);
        parser.setSchema(schema);
        return csvMapper.readValues(parser, new TypeReference<Map<String, String>>() {}).readAll();
    }

    @Override
    public boolean supportsFormat(DataFormat format) {
        return format == DataFormat.CSV;
    }
}
