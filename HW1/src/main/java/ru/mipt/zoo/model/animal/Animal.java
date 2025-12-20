package ru.mipt.zoo.model.animal;

import ru.mipt.zoo.model.IAlive;
import ru.mipt.zoo.model.IInventory;

public abstract class Animal implements IAlive, IInventory {
    protected int number;
    protected int food;

    public Animal(int number, int food) {
        this.number = number;
        this.food = food;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
