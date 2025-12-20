package ru.mipt.zoo.factory.animal.predator;

import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.predator.Wolf;

import java.util.Map;

@Component
public class WolfFactory extends PredatorFactory {

    @Override
    public boolean canCreateAnimal(Class<? extends Animal> animalClass) {
        return animalClass.equals(Wolf.class);
    }

    @Override
    public Wolf createAnimal(Map<String, Integer> params) {
        validateParameters(params);
        return new Wolf(++counter, params.get("food"));
    }
}
