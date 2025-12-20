package ru.mipt.zoo.factory.animal.herbo;

import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.herbo.Monkey;

import java.util.Map;

@Component
public class MonkeyFactory extends HerboFactory {

    @Override
    public boolean canCreateAnimal(Class<? extends Animal> animalClass) {
        return animalClass.equals(Monkey.class);
    }

    @Override
    public Monkey createAnimal(Map<String, Integer> params) {
        validateParameters(params);
        return new Monkey(++counter, params.get("food"), params.get("kindness"));
    }
}
