package ru.mipt.zoo.factory.thing;

import org.springframework.stereotype.Component;
import ru.mipt.zoo.model.thing.Table;
import ru.mipt.zoo.model.thing.Thing;

@Component
public class TableFactory extends ThingFactory {

    @Override
    public boolean canCreateThing(Class<? extends Thing> thingClass) {
        return thingClass.equals(Table.class);
    }

    @Override
    public Table createThing() {
        return new Table(++counter);
    }
}
