package ru.mipt.zoo.model.animal.herbo;

import ru.mipt.zoo.model.animal.Animal;

public abstract class Herbo extends Animal implements IKind {
    protected int kindness;

    public Herbo(int number, int food, int kindness) {
        super(number, food);
        this.kindness = kindness;
    }

    @Override
    public int getKindness() {
        return kindness;
    }
}
