package ru.mipt.finance.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImporterComposite {
    private final List<DataImporter> dataImporters;

    @Autowired
    public ImporterComposite(List<DataImporter> dataImporters) {
        this.dataImporters = dataImporters;
    }

    public void importData(DataFormat format, File file) throws IOException {
        for (DataImporter dataImporter : dataImporters) {
            if (dataImporter.supportsFormat(format)) {
                dataImporter.importData(file);
                return;
            }
        }
    }
}
