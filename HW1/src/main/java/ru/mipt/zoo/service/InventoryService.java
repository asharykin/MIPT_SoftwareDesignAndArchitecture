package ru.mipt.zoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.zoo.factory.thing.ThingFactory;
import ru.mipt.zoo.model.thing.Thing;
import ru.mipt.zoo.repository.ThingRepository;

import java.util.List;

@Service
public class InventoryService {
    private final ThingRepository thingRepository;
    private final List<ThingFactory> thingFactories;

    @Autowired
    public InventoryService(ThingRepository thingRepository, List<ThingFactory> thingFactories) {
        this.thingRepository = thingRepository;
        this.thingFactories = thingFactories;
    }

    public void addThing(Class<? extends Thing> thingClass) {
        for (ThingFactory thingFactory : thingFactories) {
            if (thingFactory.canCreateThing(thingClass)) {
                Thing thing = thingFactory.createThing();
                thingRepository.addThing(thing);
                System.out.println(thing + " has been successfully added to the inventory.");
                return;
            }
        }
    }

    public void countThings() {
        int thingCount = thingRepository.getTotalThingCount();
        System.out.println("There are " + thingCount + " things in the inventory.");
    }
}
