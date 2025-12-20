package ru.mipt.zoo.model.animal.predator;

public class Wolf extends Predator {

    public Wolf(int number, int food) {
        super(number, food);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "number=" + number +
                ", food=" + food +
                '}';
    }
}
