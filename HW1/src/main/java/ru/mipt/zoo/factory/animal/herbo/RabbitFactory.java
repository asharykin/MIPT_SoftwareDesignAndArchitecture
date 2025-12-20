package ru.mipt.zoo.factory.animal.herbo;

import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.herbo.Rabbit;

import java.util.Map;

@Component
public class RabbitFactory extends HerboFactory {

    @Override
    public boolean canCreateAnimal(Class<? extends Animal> animalClass) {
        return animalClass.equals(Rabbit.class);
    }

    @Override
    public Rabbit createAnimal(Map<String, Integer> params) {
        validateParameters(params);
        return new Rabbit(++counter, params.get("food"), params.get("kindness"));
    }
}
