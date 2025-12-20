package ru.mipt.zoo.factory.thing;

import ru.mipt.zoo.model.thing.Thing;

public abstract class ThingFactory {
    protected int counter = 0;

    public abstract boolean canCreateThing(Class<? extends Thing> thingClass);

    public abstract Thing createThing();
}
