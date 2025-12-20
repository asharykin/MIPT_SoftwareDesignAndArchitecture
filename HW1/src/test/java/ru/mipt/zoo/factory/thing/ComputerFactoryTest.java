package ru.mipt.zoo.factory.thing;

import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.thing.Computer;
import ru.mipt.zoo.model.thing.Table;

import static org.junit.jupiter.api.Assertions.*;

class ComputerFactoryTest {

    private final ComputerFactory computerFactory = new ComputerFactory();

    @Test
    void testCanCreateThing() {
        assertTrue(computerFactory.canCreateThing(Computer.class));
        assertFalse(computerFactory.canCreateThing(Table.class));
    }

    @Test
    void testCreateThing() {
        Computer computer1 = computerFactory.createThing();
        Computer computer2 = computerFactory.createThing();

        assertNotNull(computer1);
        assertEquals(2, computer2.getNumber());
    }
}
