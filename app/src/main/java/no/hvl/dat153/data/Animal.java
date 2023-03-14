package no.hvl.dat153.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animals")
public class Animal implements Comparable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String name;
    private byte[] image;

    public Animal(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int compareTo(Object o) {
        Animal a = (Animal) o;
        return name.compareTo(a.getName());
    }
}
