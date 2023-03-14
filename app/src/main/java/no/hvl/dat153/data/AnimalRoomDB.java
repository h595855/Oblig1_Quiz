package no.hvl.dat153.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Animal.class}, version = 1)
public abstract class AnimalRoomDB extends RoomDatabase {

    private static volatile AnimalRoomDB INSTANCE;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static AnimalRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AnimalRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AnimalRoomDB.class, "animals").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract AnimalDAO animalDAO();
}
