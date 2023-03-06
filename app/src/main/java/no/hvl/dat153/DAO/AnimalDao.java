package no.hvl.dat153.DAO;

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

    @Delete
    void delete(Animal animal);

    @Query("SELECT * FROM Animal")
    List<Animal> getAllAnimals();

    @Query("SELECT * FROM Animal WHERE id = :id")
    Animal getAnimalById(int id);
}
