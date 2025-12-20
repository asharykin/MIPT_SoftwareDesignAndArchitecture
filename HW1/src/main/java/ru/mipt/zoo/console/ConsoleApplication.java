package ru.mipt.zoo.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.animal.herbo.Monkey;
import ru.mipt.zoo.model.animal.herbo.Rabbit;
import ru.mipt.zoo.model.animal.predator.Tiger;
import ru.mipt.zoo.model.animal.predator.Wolf;
import ru.mipt.zoo.model.thing.Computer;
import ru.mipt.zoo.model.thing.Table;
import ru.mipt.zoo.service.InventoryService;
import ru.mipt.zoo.service.ZooService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner {
    private final Scanner scanner;
    private final ZooService zooService;
    private final InventoryService inventoryService;

    @Autowired
    public ConsoleApplication(Scanner scanner, ZooService zooService, InventoryService inventoryService) {
        this.scanner = scanner;
        this.zooService = zooService;
        this.inventoryService = inventoryService;
    }

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readIntInput();

            try {
                switch (choice) {
                    case 1 -> addAnimal();
                    case 2 -> addThing();
                    case 3 -> countAnimals();
                    case 4 -> countThings();
                    case 5 -> countNecessaryFood();
                    case 6 -> listAnimalsForPettingZoo();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add an animal to the zoo");
        System.out.println("2. Add a thing to the inventory");
        System.out.println("3. How many animals are there in the zoo?");
        System.out.println("4. How many things are there in the inventory?");
        System.out.println("5. How many kilograms of food do all animals in the zoo need in a day?");
        System.out.println("6. List the animals that can be moved to the petting zoo");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addAnimal() {
        System.out.println("\nSelect which kind of animal you want to add: ");
        System.out.println("1. Monkey");
        System.out.println("2. Rabbit");
        System.out.println("3. Tiger");
        System.out.println("4. Wolf");
        System.out.print("Enter your choice: ");

        int choice = readIntInput();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        Map<String, Integer> params = new HashMap<>();

        if (choice == 1 || choice == 2) {
            System.out.print("Enter kindness level: ");
            int kindness = readIntInput();
            params.put("kindness", kindness);
        }

        System.out.print("Enter daily food consumption: ");
        int food = readIntInput();
        params.put("food", food);

        switch (choice) {
            case 1 -> zooService.addAnimal(Monkey.class, params);
            case 2 -> zooService.addAnimal(Rabbit.class, params);
            case 3 -> zooService.addAnimal(Tiger.class, params);
            case 4 -> zooService.addAnimal(Wolf.class, params);
        }
    }

    private void addThing() {
        System.out.println("\nSelect which kind of thing you want to add: ");
        System.out.println("1. Table");
        System.out.println("2. Computer");
        System.out.print("Enter your choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1 -> inventoryService.addThing(Table.class);
            case 2 -> inventoryService.addThing(Computer.class);
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void countAnimals() {
        zooService.countAnimals();
    }

    private void countThings() {
        inventoryService.countThings();
    }

    private void countNecessaryFood() {
        zooService.countNecessaryFood();
    }

    private void listAnimalsForPettingZoo() {
        zooService.listAnimalsForPettingZoo();
    }

    private int readIntInput() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
