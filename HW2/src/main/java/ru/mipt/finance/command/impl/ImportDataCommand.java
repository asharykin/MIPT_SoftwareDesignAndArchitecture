package ru.mipt.finance.command.impl;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.importer.ImporterComposite;
import ru.mipt.finance.importer.DataFormat;

import java.io.File;
import java.io.IOException;

public class ImportDataCommand implements Command {
    private final ImporterComposite facade;

    private final DataFormat format;
    private final File file;

    public ImportDataCommand(ImporterComposite facade, DataFormat format, File file) {
        this.facade = facade;
        this.format = format;
        this.file = file;
    }

    @Override
    public void execute() {
        try {
            facade.importData(format, file);
            System.out.println("Data imported successfully from: " + file.getAbsolutePath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
