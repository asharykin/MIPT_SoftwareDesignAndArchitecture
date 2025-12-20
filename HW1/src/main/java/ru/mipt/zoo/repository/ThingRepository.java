package ru.mipt.zoo.repository;

import org.springframework.stereotype.Repository;
import ru.mipt.zoo.model.thing.Thing;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ThingRepository {
    private final List<Thing> things = new ArrayList<>();

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public int getTotalThingCount() {
        return things.size();
    }
}
