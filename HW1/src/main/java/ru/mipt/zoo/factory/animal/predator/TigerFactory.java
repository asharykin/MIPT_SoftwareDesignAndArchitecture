package ru.mipt.zoo.factory.animal.predator;

import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.predator.Tiger;

import java.util.Map;

@Component
public class TigerFactory extends PredatorFactory {

    @Override
    public boolean canCreateAnimal(Class<? extends Animal> animalClass) {
        return animalClass.equals(Tiger.class);
    }

    @Override
    public Tiger createAnimal(Map<String, Integer> params) {
        validateParameters(params);
        return new Tiger(++counter, params.get("food"));
    }
}
