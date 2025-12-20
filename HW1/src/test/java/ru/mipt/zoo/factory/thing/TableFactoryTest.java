package ru.mipt.zoo.factory.thing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.thing.Computer;
import ru.mipt.zoo.model.thing.Table;

import static org.junit.jupiter.api.Assertions.*;

class TableFactoryTest {

    private TableFactory tableFactory;

    @BeforeEach
    void setUp() {
        tableFactory = new TableFactory();
    }

    @Test
    void testCanCreateThing() {
        assertTrue(tableFactory.canCreateThing(Table.class));
        assertFalse(tableFactory.canCreateThing(Computer.class));
    }

    @Test
    void testCreateThing() {
        Table table = tableFactory.createThing();

        assertNotNull(table);
        assertEquals(1, table.getNumber());
    }
}
