package ru.mipt.finance.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.mipt.finance.command.Command;
import ru.mipt.finance.command.ExecutionTimeDecorator;

import java.util.Scanner;

public abstract class BaseConsole {
    protected Scanner scanner;
    protected ApplicationContext context;

    @Autowired
    private void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Autowired
    private void setContext(ApplicationContext context) {
        this.context = context;
    }

    protected int readIntInput() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    protected void executeCommand(Command command, boolean trackTime) {
        if (trackTime) {
            ExecutionTimeDecorator wrappedCommand = new ExecutionTimeDecorator(command);
            wrappedCommand.execute();
        } else {
            command.execute();
        }
    }
}
