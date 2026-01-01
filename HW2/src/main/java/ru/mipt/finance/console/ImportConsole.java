package ru.mipt.finance.console;

import org.springframework.stereotype.Component;
import ru.mipt.finance.command.impl.ImportDataCommand;
import ru.mipt.finance.importer.DataFormat;
import ru.mipt.finance.importer.ImporterComposite;

import java.io.File;
import java.util.Scanner;

import static ru.mipt.finance.console.ConsoleUtils.executeCommand;
import static ru.mipt.finance.console.ConsoleUtils.readIntInput;

@Component
public class ImportConsole {
    private final ImporterComposite importerComposite;
    private final Scanner scanner;

    public ImportConsole(ImporterComposite importerComposite, Scanner scanner) {
        this.importerComposite = importerComposite;
        this.scanner = scanner;
    }

    public void importData() {
        printMenu();

        int formatChoice = readIntInput(scanner);

        DataFormat format;
        switch (formatChoice) {
            case 1 -> format = DataFormat.JSON;
            case 2 -> format = DataFormat.YAML;
            case 3 -> format = DataFormat.CSV;
            default -> {
                System.out.println("Invalid option. Please try again.");
                return;
            }
        }

        System.out.print("Enter path to directory with input files: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        ImportDataCommand command = new ImportDataCommand(importerComposite, format, file);
        executeCommand(command, true);
    }

    private void printMenu() {
        System.out.println("\n===== IMPORT DATA =====");
        System.out.println("Select import format:");
        System.out.println("1. JSON");
        System.out.println("2. YAML");
        System.out.println("3. CSV");
        System.out.print("Enter choice (1, 2 or 3): ");
    }
}
