package no.hvl.dat153.Database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import no.hvl.dat153.Classes.Animal;


@Database(entities = {Animal.class}, version = 2)
public abstract class AnimalDatabase extends RoomDatabase {

    public abstract AnimalDao animalDao();
    private static AnimalDatabase instance;


    public static AnimalDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AnimalDatabase.class) {
                instance = Room.databaseBuilder(context, AnimalDatabase.class, "AnimalDatabase")
                        .addMigrations(new Migration(1, 2) {
                            @Override
                            public void migrate(@NonNull SupportSQLiteDatabase database) {
                                // Add your migration code here
                                database.execSQL("ALTER TABLE Animal ADD COLUMN weight INTEGER");
                            }
                        })
                        .build();
            }
        }
        return instance;
    }
}


