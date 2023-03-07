package no.hvl.dat153.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import no.hvl.dat153.Classes.Animal;

public class AnimalRepository {

    private  MutableLiveData<List<Animal>> searchResults = new MutableLiveData<>();
    private  AnimalDao animalDao;
    private  LiveData<List<Animal>> allAnimals;

    public LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }
    public MutableLiveData<List<Animal>> getSearchResults() {
        return searchResults;
    }
    public AnimalRepository(Application application) {
        AnimalDatabase database = AnimalDatabase.getDatabase(application);
        animalDao = database.animalDao();
        allAnimals = animalDao.getAllAnimals();
    }

    public void insert(Animal animal){
        InsertAsyncTask task = new InsertAsyncTask(animalDao);
            task.execute(animal);
    }

    public void delete(Animal animal){
        InsertAsyncTask task = new InsertAsyncTask(animalDao);
        task.execute(animal);
    }





    private static class InsertAsyncTask extends AsyncTask<Animal, Void, Void> {

    private final AnimalDao asyncTaskDao;

    InsertAsyncTask(AnimalDao dao) {
        asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Animal... params) {
        asyncTaskDao.insertAll(params[0]);
        return null;
    }
}


    private static class DeleteAsyncTask extends AsyncTask<Animal, Void, Void> {

    private final AnimalDao asyncTaskDao;

    DeleteAsyncTask(AnimalDao dao) {
        asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Animal... params) {
        asyncTaskDao.delete(params[0]);
        return null;
     }
    }
}

