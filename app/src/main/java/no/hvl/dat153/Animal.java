package no.hvl.dat153;

import android.media.Image;

import androidx.annotation.DrawableRes;

public class Animal {

    private int id;

    private String name;
    private String Uri;


    public Animal(int id, String name, String Uri) {
        this.id = id;
        this.name = name;
        this.Uri = Uri;
    }
    //private Byte[] bytes;

    public String getName() {
        return name;
    }

    public String getUri() {
        return Uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
