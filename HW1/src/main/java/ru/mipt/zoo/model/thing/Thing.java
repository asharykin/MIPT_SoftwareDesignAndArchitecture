package ru.mipt.zoo.model.thing;

import ru.mipt.zoo.model.IInventory;

public abstract class Thing implements IInventory {
    protected int number;

    protected Thing(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
