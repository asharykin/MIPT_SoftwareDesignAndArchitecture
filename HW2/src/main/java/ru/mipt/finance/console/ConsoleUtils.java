package ru.mipt.finance.console;

import ru.mipt.finance.command.Command;
import ru.mipt.finance.command.ExecutionTimeDecorator;

import java.util.Scanner;

public class ConsoleUtils {

    public static int readIntInput(Scanner scanner) {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void executeCommand(Command command, boolean trackTime) {
        if (trackTime) {
            ExecutionTimeDecorator wrappedCommand = new ExecutionTimeDecorator(command);
            wrappedCommand.execute();
        } else {
            command.execute();
        }
    }
}
