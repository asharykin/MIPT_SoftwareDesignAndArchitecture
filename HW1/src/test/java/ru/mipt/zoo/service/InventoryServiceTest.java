package ru.mipt.zoo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mipt.zoo.factory.thing.ThingFactory;
import ru.mipt.zoo.model.thing.Computer;
import ru.mipt.zoo.model.thing.Table;
import ru.mipt.zoo.model.thing.Thing;
import ru.mipt.zoo.repository.ThingRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private ThingRepository thingRepository;

    @Mock
    private ThingFactory thingFactory;

    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<ThingFactory> thingFactories = new ArrayList<>();
        thingFactories.add(thingFactory);
        inventoryService = new InventoryService(thingRepository, thingFactories);
    }

    @Test
    void testAddThing_WhenFactoryCanCreateTable() {
        Class<? extends Thing> tableClass = Table.class;
        Table table = new Table(1);

        when(thingFactory.canCreateThing(tableClass)).thenReturn(true);
        when(thingFactory.createThing()).thenReturn(table);

        inventoryService.addThing(tableClass);

        verify(thingRepository, times(1)).addThing(table);
    }

    @Test
    void testAddThing_WhenFactoryCanCreateComputer() {
        Class<? extends Thing> computerClass = Computer.class;
        Computer computer = new Computer(1);

        when(thingFactory.canCreateThing(computerClass)).thenReturn(true);
        when(thingFactory.createThing()).thenReturn(computer);

        inventoryService.addThing(computerClass);

        verify(thingRepository, times(1)).addThing(computer);
    }

    @Test
    void testAddThing_WhenFactoryCannotCreate() {
        Class<? extends Thing> thingClass = Table.class;

        when(thingFactory.canCreateThing(thingClass)).thenReturn(false);

        inventoryService.addThing(thingClass);

        verify(thingRepository, never()).addThing(any(Thing.class));
    }

    @Test
    void testCountThings() {
        when(thingRepository.getTotalThingCount()).thenReturn(5);

        inventoryService.countThings();

        verify(thingRepository, times(1)).getTotalThingCount();
    }
}
