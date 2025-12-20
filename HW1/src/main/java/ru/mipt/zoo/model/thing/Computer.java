package ru.mipt.zoo.model.thing;

public class Computer extends Thing {

    public Computer(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "number=" + number +
                '}';
    }
}
