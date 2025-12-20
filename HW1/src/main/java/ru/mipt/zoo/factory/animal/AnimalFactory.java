package ru.mipt.zoo.factory.animal;

import ru.mipt.zoo.model.animal.Animal;

import java.util.Map;

public abstract class AnimalFactory {
    protected int counter = 0;

    protected void validateParameters(Map<String, Integer> params) {
        validateFood(params.get("food"));
    }

    private void validateFood(int food) {
        if (food <= 0) {
            throw new IllegalArgumentException("An animal cannot eat 0 or less kilograms of food in a day");
        }
        if (food > 50) {
            throw new IllegalArgumentException("An animal cannot eat more than 50 kilograms of food in a day");
        }
    }

    public abstract boolean canCreateAnimal(Class<? extends Animal> animalClass);

    public abstract Animal createAnimal(Map<String, Integer> params);
}
