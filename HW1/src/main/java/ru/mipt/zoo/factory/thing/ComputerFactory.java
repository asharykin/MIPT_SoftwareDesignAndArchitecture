package ru.mipt.zoo.factory.thing;

import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.thing.Computer;
import ru.mipt.zoo.model.thing.Thing;

@Component
public class ComputerFactory extends ThingFactory {

    @Override
    public boolean canCreateThing(Class<? extends Thing> thingClass) {
        return thingClass.equals(Computer.class);
    }

    @Override
    public Computer createThing() {
        return new Computer(++counter);
    }
}
