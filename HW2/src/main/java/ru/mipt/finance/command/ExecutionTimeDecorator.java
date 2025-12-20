package ru.mipt.finance.command;

public class ExecutionTimeDecorator implements Command {
    private final Command command;

    public ExecutionTimeDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long start = System.nanoTime();
        command.execute();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1000000 + " ms");
    }
}
