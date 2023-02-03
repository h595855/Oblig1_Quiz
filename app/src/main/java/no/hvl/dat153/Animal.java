package no.hvl.dat153;

import android.media.Image;

import androidx.annotation.DrawableRes;

public class Animal {

    private int id;

    private String name;
    private int image;


    public Animal(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    //private Byte[] bytes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
