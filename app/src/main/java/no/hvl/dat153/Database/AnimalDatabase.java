package no.hvl.dat153.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import no.hvl.dat153.Classes.Animal;


@Database(entities = {Animal.class}, version = 1)
public abstract class AnimalDatabase extends RoomDatabase {

    public abstract AnimalDao animalDao();
    private static AnimalDatabase instance;

    public static AnimalDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AnimalDatabase.class) {
                instance = Room.databaseBuilder(context, AnimalDatabase.class, "AnimalDatabase").build();
            }
        }
        return instance;
    }
}


