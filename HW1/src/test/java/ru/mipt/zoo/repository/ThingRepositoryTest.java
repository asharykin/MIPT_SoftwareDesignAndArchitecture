package ru.mipt.zoo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.thing.Computer;
import ru.mipt.zoo.model.thing.Table;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThingRepositoryTest {

    private ThingRepository thingRepository;

    @BeforeEach
    void setUp() {
        thingRepository = new ThingRepository();
    }

    @Test
    void testAddThing() {
        Table table = new Table(1);
        thingRepository.addThing(table);

        assertEquals(1, thingRepository.getTotalThingCount());
    }

    @Test
    void testGetTotalThingCount() {
        assertEquals(0, thingRepository.getTotalThingCount());

        thingRepository.addThing(new Table(1));
        assertEquals(1, thingRepository.getTotalThingCount());

        thingRepository.addThing(new Computer(2));
        assertEquals(2, thingRepository.getTotalThingCount());
    }
}
