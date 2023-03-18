package no.hvl.dat153.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TestAnimalRepo extends AnimalRepo {
    private MutableLiveData<List<Animal>> animalsLiveData = new MutableLiveData<>();

    public TestAnimalRepo(Application application) {
        super(application);
        animalsLiveData.setValue(new ArrayList<>());
    }

    @Override
    public LiveData<List<Animal>> getAllAnimals() {
        return animalsLiveData;
    }

    @Override
    public LiveData<Animal> getAnimalById(int id) {
        for (Animal animal : animalsLiveData.getValue()) {
            if (animal.getId() == id) {
                return new MutableLiveData<>(animal);
            }
        }
        return null;
    }

    @Override
    public void insertAnimal(Animal animal) {
        List<Animal> animals = animalsLiveData.getValue();
        animals.add(animal);
        animalsLiveData.setValue(animals);
    }

    @Override
    public void deleteAnimal(Animal animal) {
        List<Animal> animals = animalsLiveData.getValue();
        animals.remove(animal);
        animalsLiveData.setValue(animals);
    }
}
