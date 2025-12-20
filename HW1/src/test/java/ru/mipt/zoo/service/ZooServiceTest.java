package ru.mipt.zoo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mipt.zoo.factory.animal.AnimalFactory;
import ru.mipt.zoo.model.animal.Animal;
import ru.mipt.zoo.model.animal.herbo.Herbo;
import ru.mipt.zoo.model.animal.herbo.Rabbit;
import ru.mipt.zoo.model.animal.predator.Tiger;
import ru.mipt.zoo.repository.AnimalRepository;

import java.util.*;

import static org.mockito.Mockito.*;

class ZooServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private VeterinaryClinic veterinaryClinic;

    @Mock
    private AnimalFactory animalFactory;

    private ZooService zooService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<AnimalFactory> animalFactories  = new ArrayList<>();
        animalFactories.add(animalFactory);
        zooService = new ZooService(animalRepository, veterinaryClinic, animalFactories);
    }

    @Test
    void testAddAnimal_WhenHealthyAndFactoryCanCreate() {
        Class<? extends Animal> rabbitClass = Rabbit.class;
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 10);
        params.put("kindness", 8);
        Rabbit rabbit = new Rabbit(1, 5, 6);

        when(veterinaryClinic.isAnimalHealthy()).thenReturn(true);
        when(animalFactory.canCreateAnimal(rabbitClass)).thenReturn(true);
        when(animalFactory.createAnimal(params)).thenReturn(rabbit);

        zooService.addAnimal(rabbitClass, params);

        verify(animalRepository, times(1)).addAnimal(rabbit);
    }

    @Test
    void testAddAnimal_WhenUnhealthy() {
        Class<? extends Animal> tigerClass = Tiger.class;
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 25);

        when(veterinaryClinic.isAnimalHealthy()).thenReturn(false);

        zooService.addAnimal(tigerClass, params);

        verify(animalRepository, never()).addAnimal(any(Animal.class));
    }

    @Test
    void testCountAnimals() {
        when(animalRepository.getTotalAnimalCount()).thenReturn(8);

        zooService.countAnimals();

        verify(animalRepository, times(1)).getTotalAnimalCount();
    }

    @Test
    void testCountNecessaryFood() {
        when(animalRepository.getTotalNecessaryFoodWeight()).thenReturn(50);

        zooService.countNecessaryFood();

        verify(animalRepository, times(1)).getTotalNecessaryFoodWeight();
    }

    @Test
    void testListAnimalsForPettingZoo() {
        Rabbit rabbit = new Rabbit(1, 5, 6);
        List<Herbo> kindAnimals = Collections.singletonList(rabbit);
        when(animalRepository.getKindAnimals()).thenReturn(kindAnimals);

        zooService.listAnimalsForPettingZoo();

        verify(animalRepository, times(1)).getKindAnimals();
    }
}