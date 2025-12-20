package ru.mipt.zoo.factory.animal.herbo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.animal.herbo.Monkey;
import ru.mipt.zoo.model.animal.herbo.Rabbit;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RabbitFactoryTest {

    private RabbitFactory rabbitFactory;

    @BeforeEach
    void setUp() {
        rabbitFactory = new RabbitFactory();
    }

    @Test
    void testCanCreateAnimal() {
        assertTrue(rabbitFactory.canCreateAnimal(Rabbit.class));
        assertFalse(rabbitFactory.canCreateAnimal(Monkey.class));
    }

    @Test
    void testCreateAnimal() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 8);
        params.put("kindness", 5);

        Rabbit rabbit = rabbitFactory.createAnimal(params);

        assertNotNull(rabbit);
        assertEquals(1, rabbit.getNumber());
        assertEquals(8, rabbit.getFood());
        assertEquals(5, rabbit.getKindness());
    }

    @Test
    void testValidateParametersThrowsException() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 8);
        params.put("kindness", 11);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> rabbitFactory.createAnimal(params));

        assertEquals("Kindness cannot be greater than 10", exception.getMessage());
    }
}
