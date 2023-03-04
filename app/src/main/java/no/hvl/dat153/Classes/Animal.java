package no.hvl.dat153.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.*;

import androidx.room.Entity;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Entity(tableName = "Animal")
public class Animal implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "picture")
    private Bitmap image;

    public Animal(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    /*
    in order for Animal class to be parcelable
    need to convert Bitmap to bytearray
    or i could solve this easier by just passing the
    uri & creating Bitmap inside of this class
     */
    protected Animal(Parcel in) {
        name = in.readString();
        byte[] byteArray = in.createByteArray();
        image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        dest.writeByteArray(stream.toByteArray());
    }
}

