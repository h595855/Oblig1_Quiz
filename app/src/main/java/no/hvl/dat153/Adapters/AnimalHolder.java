package no.hvl.dat153.Adapters;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import no.hvl.dat153.Classes.Animal;

public class AnimalHolder {

    private static MutableLiveData<List<Animal>> animals;
    private static AnimalHolder instance;

    private AnimalHolder() {
        animals = new MutableLiveData<>();
        animals.setValue(new ArrayList<>());
    }

    public static AnimalHolder getInstance() {
        if (instance == null) {
            instance = new AnimalHolder();
        }
        return instance;
    }

    public static void addAnimal(Animal animal){
        List<Animal> currentList = animals.getValue();
        currentList.add(animal);
        animals.setValue(currentList);
    }

    public static MutableLiveData<List<Animal>> getAnimals() {
        return animals;
    }
}
