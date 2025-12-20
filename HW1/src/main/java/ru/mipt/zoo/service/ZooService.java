package ru.mipt.zoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.zoo.factory.animal.AnimalFactory;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.herbo.Herbo;
import ru.mipt.zoo.repository.AnimalRepository;

import java.util.List;
import java.util.Map;

@Service
public class ZooService {
    private final AnimalRepository animalRepository;
    private final VeterinaryClinic veterinaryClinic;
    private final List<AnimalFactory> animalFactories;

    @Autowired
    public ZooService(AnimalRepository animalRepository, VeterinaryClinic veterinaryClinic, List<AnimalFactory> animalFactories) {
        this.animalRepository = animalRepository;
        this.veterinaryClinic = veterinaryClinic;
        this.animalFactories = animalFactories;
    }

    public void addAnimal(Class<? extends Animal> animalClass, Map<String, Integer> params) {
        if (veterinaryClinic.isAnimalHealthy()) {
            for (AnimalFactory animalFactory : animalFactories) {
                if (animalFactory.canCreateAnimal(animalClass)) {
                    Animal animal = animalFactory.createAnimal(params);
                    animalRepository.addAnimal(animal);
                    System.out.println(animal + " has been successfully added to the zoo.");
                    return;
                }
            }
        }
        System.out.println("Unfortunately, your animal has been rejected by the veterinary clinic due to health issues.");
    }

    public void countAnimals() {
        int animalCount = animalRepository.getTotalAnimalCount();
        System.out.println("There are " + animalCount + " animals in the zoo.");
    }

    public void countNecessaryFood() {
        int foodWeight = animalRepository.getTotalNecessaryFoodWeight();
        System.out.println("All animals in the zoo need " + foodWeight + " kilograms of food in a day.");
    }

    public void listAnimalsForPettingZoo() {
        List<Herbo> kindAnimals = animalRepository.getKindAnimals();
        System.out.println("The following animals can be moved to the petting zoo: ");
        for (Herbo animal : kindAnimals) {
            System.out.println(animal);
        }
    }
}
