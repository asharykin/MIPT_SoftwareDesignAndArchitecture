package ru.mipt.zoo.factory.animal.predator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.animal.predator.Tiger;
import ru.mipt.zoo.model.animal.predator.Wolf;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TigerFactoryTest {

    private TigerFactory tigerFactory;

    @BeforeEach
    void setUp() {
        tigerFactory = new TigerFactory();
    }

    @Test
    void testCanCreateAnimal() {
        assertTrue(tigerFactory.canCreateAnimal(Tiger.class));
        assertFalse(tigerFactory.canCreateAnimal(Wolf.class));
    }

    @Test
    void testCreateAnimal() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 20);

        Tiger tiger = tigerFactory.createAnimal(params);

        assertNotNull(tiger);
        assertEquals(1, tiger.getNumber());
        assertEquals(20, tiger.getFood());
    }

    @Test
    void testValidateParametersThrowsException() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> tigerFactory.createAnimal(params));

        assertEquals("An animal cannot eat 0 or less kilograms of food in a day", exception.getMessage());
    }
}
