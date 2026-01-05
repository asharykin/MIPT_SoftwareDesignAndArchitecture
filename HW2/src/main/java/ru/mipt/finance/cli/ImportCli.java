package ru.mipt.finance.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.ImportDataCommand;
import ru.mipt.finance.importer.DataFormat;
import ru.mipt.finance.importer.ImporterComposite;

import java.io.File;

@Component
public class ImportCli extends BaseCli {
    private final ImporterComposite importerComposite;

    @Autowired
    public ImportCli(ImporterComposite importerComposite) {
        this.importerComposite = importerComposite;
    }

    public void importData() {
        printMenu();

        int choice = readIntInput();

        DataFormat format = getFormat(choice);

        if (format != null) {
            System.out.print("Enter path to the directory with input files: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            ImportDataCommand command = new ImportDataCommand(importerComposite, format, file);
            executeCommand(command, true);
        }
    }

    private void printMenu() {
        System.out.println("\n===== IMPORT DATA =====");
        System.out.println("Select import format:");
        System.out.println("1. JSON");
        System.out.println("2. YAML");
        System.out.println("3. CSV");
        System.out.print("Enter your choice (1, 2 or 3): ");
    }

    private DataFormat getFormat(int choice) {
        switch (choice) {
            case 1 -> {
                return DataFormat.JSON;
            }
            case 2 -> {
                return DataFormat.YAML;
            }
            case 3 -> {
                return DataFormat.CSV;
            }
            default -> {
                System.out.println("Invalid option. Please try again.");
                return null;
            }
        }
    }
}
