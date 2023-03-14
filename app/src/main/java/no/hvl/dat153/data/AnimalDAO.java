package no.hvl.dat153.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnimalDAO {
    @Query("SELECT * FROM animals")
    LiveData<List<Animal>> getAllAnimals();

    @Query("SELECT * FROM animals WHERE id = :id")
    LiveData<Animal> getAnimalById(int id);

    @Insert
    void insertAnimal(Animal animal);

    @Delete
    void deleteAnimal(Animal animal);
}
