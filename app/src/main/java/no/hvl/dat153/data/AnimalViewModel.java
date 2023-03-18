package no.hvl.dat153.data;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {

    private AnimalRepo animalRepo;
    private LiveData<List<Animal>> allAnimals;

    public AnimalViewModel(Application application) {
        super(application);
        animalRepo = new AnimalRepo(application);
        allAnimals = animalRepo.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    public LiveData<Animal> getAnimalById(int id) {
        return animalRepo.getAnimalById(id);
    }

    public void insertAnimal(Animal animal) {
        animalRepo.insertAnimal(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepo.deleteAnimal(animal);
    }

    public void setRepo(AnimalRepo repo) {
        animalRepo = repo;
        allAnimals = animalRepo.getAllAnimals();
    }


}
