package ru.mipt.zoo.factory.animal.predator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.animal.predator.Tiger;
import ru.mipt.zoo.model.animal.predator.Wolf;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WolfFactoryTest {

    private WolfFactory wolfFactory;

    @BeforeEach
    void setUp() {
        wolfFactory = new WolfFactory();
    }

    @Test
    void testCanCreateAnimal() {
        assertTrue(wolfFactory.canCreateAnimal(Wolf.class));
        assertFalse(wolfFactory.canCreateAnimal(Tiger.class));
    }

    @Test
    void testCreateAnimal() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 15);

        Wolf wolf = wolfFactory.createAnimal(params);

        assertNotNull(wolf);
        assertEquals(1, wolf.getNumber());
        assertEquals(15, wolf.getFood());
    }

    @Test
    void testValidateParametersThrowsException() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 60);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> wolfFactory.createAnimal(params));

        assertEquals("An animal cannot eat more than 50 kilograms of food in a day", exception.getMessage());
    }
}
