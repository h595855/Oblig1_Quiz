package no.hvl.dat153;

import android.graphics.Bitmap;
import android.media.Image;

import androidx.annotation.DrawableRes;

public class Animal {

    private int id;

    private String name;
    private Bitmap image;


    public Animal(String name, Bitmap image) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
