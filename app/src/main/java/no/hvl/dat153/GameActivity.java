package no.hvl.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Animal cat = new Animal(1,"Cat", "@Drawable/cat");
    Animal dog = new Animal(2,"dog", "@Drawable/dog");
    Animal among = new Animal(3,"among", "@Drawable/among");

    ArrayList<Animal> items = new ArrayList<Animal>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ArrayList<Integer> test = new ArrayList<>();

        int catInt = this.getResources().getIdentifier("cat", "drawable", this.getPackageName());
        int amongInt = this.getResources().getIdentifier("among", "drawable", this.getPackageName());
        int dogInt = this.getResources().getIdentifier("dog", "drawable", this.getPackageName());

        items.add(cat);
        items.add(dog);
        items.add(among);

        test.add(catInt);
        test.add(amongInt);
        test.add(dogInt);


        Random ran = new Random();
        int randnum = ran.nextInt(items.size());

        int randint = ran.nextInt(test.size());

        Animal animal = items.get(randnum);

        int randome = test.get(randint);

        ImageView img = (ImageView) findViewById(R.id.image);

        img.setImageResource(randome);

        Button option1 = findViewById(R.id.alt1);
        Button option2 = findViewById(R.id.alt2);
        Button option3 = findViewById(R.id.alt3);

        option1.setText("Dog");
        option2.setText("Cade");
        option3.setText("Among us");

    }


}