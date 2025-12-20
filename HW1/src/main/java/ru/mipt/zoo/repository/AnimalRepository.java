package ru.mipt.zoo.repository;

import org.springframework.stereotype.Repository;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.herbo.Herbo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public int getTotalAnimalCount() {
        return animals.size();
    }

    public int getTotalNecessaryFoodWeight() {
        int foodWeight = 0;
        for (Animal animal : animals) {
            foodWeight += animal.getFood();
        }
        return foodWeight;
    }

    public List<Herbo> getKindAnimals() {
        List<Herbo> kindAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Herbo herbo && herbo.getKindness() > 5) {
                kindAnimals.add(herbo);
            }
        }
        return kindAnimals;
    }
}
