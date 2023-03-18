package no.hvl.dat153.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AnimalRepo {

    private AnimalDAO animalDAO;
    private LiveData<List<Animal>> allAnimals;

    public AnimalRepo(Application application) {
        AnimalRoomDB db = AnimalRoomDB.getDatabase(application);
        animalDAO = db.animalDAO();
        allAnimals = animalDAO.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return animalDAO.getAllAnimals();
    }

    public LiveData<Animal> getAnimalById(int id) {
        return animalDAO.getAnimalById(id);
    }

    public void insertAnimal(Animal animal) {
        AnimalRoomDB.databaseWriteExecutor.execute(() -> {
            animalDAO.insertAnimal(animal);
        });
    }

    public void deleteAnimal(Animal animal) {
        AnimalRoomDB.databaseWriteExecutor.execute(() -> {
            animalDAO.deleteAnimal(animal);
        });
    }
}
