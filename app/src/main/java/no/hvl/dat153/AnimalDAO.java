package no.hvl.dat153;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends Application {

    private static List<Animal> AnimalList = new ArrayList<Animal>();

    @Override
    public void onCreate() {
        Animal a1 = new Animal("Cat", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
        Animal a2 = new Animal("dog", BitmapFactory.decodeResource(this.getResources(), R.drawable.dog));
        Animal a3 = new Animal("among", BitmapFactory.decodeResource(this.getResources(), R.drawable.among));
        AnimalList.add(a1);
        AnimalList.add(a2);
        AnimalList.add(a3);
    }

    public void addAnimal(Animal a){
        AnimalList.add(a);
    }

    public static List<Animal> getAnimalList(){
        return AnimalList;
    }

    public static Bitmap getBitmap(String n){
        Bitmap bitmap = null;
        boolean found = false;
        for(int i = 0; i < AnimalList.size() && !found; i++){
            if(AnimalList.get(i).getName().equals(n)){
                bitmap = AnimalList.get(i).getImage();
                found = true;
            }
        }
        return bitmap;
    }
}
