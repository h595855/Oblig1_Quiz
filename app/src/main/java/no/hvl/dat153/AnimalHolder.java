package no.hvl.dat153;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class AnimalHolder {

    public static ArrayList<Animal> animals;
    private static AnimalHolder instance;

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
