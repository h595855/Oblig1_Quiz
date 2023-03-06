package no.hvl.dat153.Database;


import android.content.Context;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.DAO.AnimalDao;
import no.hvl.dat153.Utils.BitmapTypeConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Animal.class}, version = 1)
@TypeConverters({BitmapTypeConverter.class})
public abstract class AnimalDatabase extends RoomDatabase {

    public abstract AnimalDao animalDao();

    private static AnimalDatabase instance;

    public static AnimalDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AnimalDatabase.class) {
                instance = Room.databaseBuilder(context, AnimalDatabase.class, "DATABASE").build();
            }
        }
        return instance;
    }

}


