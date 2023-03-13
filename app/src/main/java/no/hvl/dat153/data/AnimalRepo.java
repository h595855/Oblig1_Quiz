package no.hvl.dat153.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalRepo {

    private AnimalDAO animalDAO;
    private LiveData<List<Animal>> allAnimals;
    private final ExecutorService executorService;
    private MutableLiveData<Boolean> insertionSuccess;

    public AnimalRepo(Application application) {
        AnimalRoomDB db = AnimalRoomDB.getDatabase(application);
        animalDAO = db.animalDAO();
        allAnimals = animalDAO.getAllAnimals();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return animalDAO.getAllAnimals();
    }

    public LiveData<Animal> getAnimalById(int id) {
        return animalDAO.getAnimalById(id);
    }

    public LiveData<Boolean> getInsertionSuccess() {
        return insertionSuccess;
    }

    public void insertAnimal(final Animal animal) {
        executorService.execute(() -> {
            animalDAO.insertAnimal(animal);
            insertionSuccess.postValue(true);
        });
    }

    public void deleteAnimal(final Animal animal) {
        executorService.execute(() -> animalDAO.deleteAnimal(animal));
    }
}
