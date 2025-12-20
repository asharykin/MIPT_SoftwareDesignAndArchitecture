package ru.mipt.zoo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.herbo.Herbo;
import ru.mipt.zoo.model.animal.herbo.Rabbit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalRepositoryTest {

    private AnimalRepository animalRepository;

    @BeforeEach
    void setUp() {
        animalRepository = new AnimalRepository();
    }

    @Test
    void testAddAnimal() {
        Animal rabbit = new Rabbit(1, 5, 6);
        animalRepository.addAnimal(rabbit);

        assertEquals(1, animalRepository.getTotalAnimalCount());
    }

    @Test
    void testGetTotalAnimalCount() {
        assertEquals(0, animalRepository.getTotalAnimalCount());

        animalRepository.addAnimal(new Rabbit(1, 5, 6));
        assertEquals(1, animalRepository.getTotalAnimalCount());

        animalRepository.addAnimal(new Rabbit(2, 3, 4));
        assertEquals(2, animalRepository.getTotalAnimalCount());
    }

    @Test
    void testGetTotalNecessaryFoodWeight() {
        animalRepository.addAnimal(new Rabbit(1, 5, 6)); // food = 5
        animalRepository.addAnimal(new Rabbit(2, 10, 4)); // food = 10

        assertEquals(15, animalRepository.getTotalNecessaryFoodWeight());
    }

    @Test
    void testGetKindAnimals() {
        Rabbit kindRabbit = new Rabbit(1, 5, 7); // kindness > 5
        Rabbit unkindRabbit = new Rabbit(2, 10, 3); // kindness <= 5

        animalRepository.addAnimal(kindRabbit);
        animalRepository.addAnimal(unkindRabbit);

        List<Herbo> kindAnimals = animalRepository.getKindAnimals();

        assertEquals(1, kindAnimals.size());
        assertEquals(kindRabbit, kindAnimals.get(0));
    }
}
