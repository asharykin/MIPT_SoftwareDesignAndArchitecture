package ru.mipt.finance.importer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.finance.facade.BankAccountFacade;
import ru.mipt.finance.facade.CategoryFacade;
import ru.mipt.finance.facade.OperationFacade;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class YamlDataImporter extends DataImporter {
    private final YAMLMapper yamlMapper;

    @Autowired
    public YamlDataImporter(BankAccountFacade bankAccountFacade, CategoryFacade categoryFacade,
                            OperationFacade operationFacade, YAMLMapper yamlMapper) {
        super(bankAccountFacade, categoryFacade, operationFacade);
        this.yamlMapper = yamlMapper;
    }

    @Override
    protected Map<String, Object> parseFile(File file) throws IOException {
        return yamlMapper.readValue(file, new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public boolean supportsFormat(DataFormat format) {
        return format == DataFormat.YAML;
    }
}
