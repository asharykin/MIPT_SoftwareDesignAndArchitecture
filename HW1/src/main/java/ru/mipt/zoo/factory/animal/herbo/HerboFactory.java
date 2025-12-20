package ru.mipt.zoo.factory.animal.herbo;

import ru.mipt.zoo.factory.animal.AnimalFactory;

import java.util.Map;

public abstract class HerboFactory extends AnimalFactory {

    @Override
    protected void validateParameters(Map<String, Integer> params) {
        super.validateParameters(params);
        validateKindness(params.get("kindness"));
    }

    private void validateKindness(int kindness) {
        if (kindness < 0) {
            throw new IllegalArgumentException("Kindness cannot be negative");
        }
        if (kindness > 10) {
            throw new IllegalArgumentException("Kindness cannot be greater than 10");
        }
    }
}
