package ru.mipt.zoo.model.animal.herbo;

public class Monkey extends Herbo {

    public Monkey(int number, int food, int kindness) {
        super(number, food, kindness);
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "number=" + number +
                ", food=" + food +
                ", kindness=" + kindness +
                '}';
    }
}
