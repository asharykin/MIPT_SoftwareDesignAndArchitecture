package ru.mipt.zoo.model.animal.herbo;

public class Rabbit extends Herbo {

    public Rabbit(int number, int food, int kindness) {
        super(number, food, kindness);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "number=" + number +
                ", food=" + food +
                ", kindness=" + kindness +
                '}';
    }
}
