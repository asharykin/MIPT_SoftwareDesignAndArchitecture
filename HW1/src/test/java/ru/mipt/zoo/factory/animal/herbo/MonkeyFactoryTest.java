package ru.mipt.zoo.factory.animal.herbo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.zoo.model.animal.herbo.Monkey;
import ru.mipt.zoo.model.animal.herbo.Rabbit;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MonkeyFactoryTest {

    private MonkeyFactory monkeyFactory;

    @BeforeEach
    void setUp() {
        monkeyFactory = new MonkeyFactory();
    }

    @Test
    void testCanCreateAnimal() {
        assertTrue(monkeyFactory.canCreateAnimal(Monkey.class));
        assertFalse(monkeyFactory.canCreateAnimal(Rabbit.class));
    }

    @Test
    void testCreateAnimal() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 12);
        params.put("kindness", 7);

        Monkey monkey = monkeyFactory.createAnimal(params);

        assertNotNull(monkey);
        assertEquals(1, monkey.getNumber());
        assertEquals(12, monkey.getFood());
        assertEquals(7, monkey.getKindness());
    }

    @Test
    void testValidateParametersThrowsException() {
        Map<String, Integer> params = new HashMap<>();
        params.put("food", 12);
        params.put("kindness", -1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> monkeyFactory.createAnimal(params));

        assertEquals("Kindness cannot be negative", exception.getMessage());
    }
}
