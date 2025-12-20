package ru.mipt.zoo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VeterinaryClinic {

    public boolean isAnimalHealthy() {
        return new Random().nextInt(10) < 7;
    }
}
