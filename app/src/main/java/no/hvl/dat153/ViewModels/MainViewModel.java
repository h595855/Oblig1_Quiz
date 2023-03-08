package no.hvl.dat153.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.Database.AnimalRepository;

public class MainViewModel extends AndroidViewModel {

    private AnimalRepository repo;
    private LiveData<List<Animal>> allAnimal;

    public MainViewModel(Application application){
        super(application);
        repo = new AnimalRepository(application);
        allAnimal = repo.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals(){
        return allAnimal;
    }
    public void insert(Animal animal){
        repo.insert(animal);
    }
    public void delete(Animal animal){
        repo.delete(animal);
    }
}
