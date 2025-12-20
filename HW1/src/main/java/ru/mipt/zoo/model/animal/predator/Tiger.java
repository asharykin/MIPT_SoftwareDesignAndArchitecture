package ru.mipt.zoo.model.animal.predator;

public class Tiger extends Predator {

    public Tiger(int number, int food) {
        super(number, food);
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "number=" + number +
                ", food=" + food +
                '}';
    }
}
