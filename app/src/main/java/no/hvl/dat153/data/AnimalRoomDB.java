package no.hvl.dat153.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Animal.class}, version = 1)
public abstract class AnimalRoomDB extends RoomDatabase {

    private static AnimalRoomDB INSTANCE;

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
