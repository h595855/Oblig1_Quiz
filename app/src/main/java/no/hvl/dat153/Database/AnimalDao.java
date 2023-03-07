package no.hvl.dat153.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import no.hvl.dat153.Classes.Animal;

@Dao
public interface AnimalDao {
    @Insert
    void insert(Animal animal);

    @Update
    void update(Animal animal);

    @Insert
    void insertAll(Animal... animals);

    @Delete
    void delete(Animal animal);

    @Query("SELECT * FROM Animal")
    LiveData<List<Animal>> getAllAnimals();

    @Query("SELECT * FROM Animal WHERE name = :name")
    Animal getAnimalById(String name);
}
