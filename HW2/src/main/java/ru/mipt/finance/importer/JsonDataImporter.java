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
    protected Map<String, Object> parseFile(File file) throws IOException {
        return jsonMapper.readValue(file, new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public boolean supportsFormat(DataFormat format) {
        return format == DataFormat.JSON;
    }
}
