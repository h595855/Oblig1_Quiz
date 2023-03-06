package no.hvl.dat153.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.DAO.AnimalDao;

public class AnimalRepository {

    private AnimalDao animalDao;
    private LiveData<List<Animal>> allAnimals;

    public AnimalRepository(Application application) {
        AnimalDatabase database = AnimalDatabase.getDatabase(application);
        animalDao = database.animalDao();
        allAnimals = (LiveData<List<Animal>>) animalDao.getAllAnimals();
    }

    public void insert(Animal animal){
        animalDao.insert(animal);
    }

    public void delete(Animal animal){
        animalDao.delete(animal);
    }

}
