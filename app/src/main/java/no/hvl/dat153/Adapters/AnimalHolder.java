package no.hvl.dat153.Adapters;

import java.util.ArrayList;

import no.hvl.dat153.Classes.Animal;

public class AnimalHolder {

    public static ArrayList<Animal> animals;
    private static AnimalHolder instance;

    //experiment on having a static list of objects between activity classes
    private AnimalHolder() {
        animals = new ArrayList<>();
    }

    public static AnimalHolder getInstance() {
        if (instance == null) {
            instance = new AnimalHolder();
        }
        return instance;
    }

    public static void addAnimal(Animal animal){
        animals.add(animal);
    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }

}
