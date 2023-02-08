package no.hvl.dat153;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int stilling = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        spill();

    }

    private void spill(){

        Animal a1 = new Animal("Cat", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
        Animal a2 = new Animal("dog", BitmapFactory.decodeResource(this.getResources(), R.drawable.dog));
        Animal a3 = new Animal("among", BitmapFactory.decodeResource(this.getResources(), R.drawable.among));

        ArrayList<Animal> items = new ArrayList<Animal>();

        int catInt = this.getResources().getIdentifier("cat", "drawable", this.getPackageName());
        int amongInt = this.getResources().getIdentifier("among", "drawable", this.getPackageName());
        int dogInt = this.getResources().getIdentifier("dog", "drawable", this.getPackageName());

        items.add(a1);
        items.add(a2);
        items.add(a3);

        //Animal cat = new Animal(1, "Cat", catInt);
        //Animal dog = new Animal(2, "dog", dogInt);
        //Animal among = new Animal(3, "among", amongInt);

       // items.add(cat);
       // items.add(dog);
        // items.add(among);


        Animal animal = GetRandomAnimal(items);

        ImageView img = (ImageView) findViewById(R.id.image);

        //Set random image from random chosen animal
        img.setImageBitmap(animal.getImage());

        Button option1 = findViewById(R.id.alt1);
        Button option2 = findViewById(R.id.alt2);
        Button option3 = findViewById(R.id.alt3);


        option1.setText("Cat");
        option2.setText("dog");
        option3.setText("among");

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option1.getText() == animal.getName()) {
                    stilling++;
                    showPopUpBox("Correct! " + "\nPoints: " + stilling);
                    Animal animal2 = GetRandomAnimal(items);
                    animal.setName(animal2.getName());
                    img.setImageBitmap(animal2.getImage());
                } else {
                    showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                    Animal animal2 = GetRandomAnimal(items);
                    animal.setName(animal2.getName());
                    img.setImageBitmap(animal2.getImage());
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option2.getText() == animal.getName()) {
                    stilling++;
                    showPopUpBox("Correct! " + "\nPoints: " + stilling);
                    Animal animal2 = GetRandomAnimal(items);
                    animal.setName(animal2.getName());
                    img.setImageBitmap(animal2.getImage());
                } else {
                    showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                    Animal animal2 = GetRandomAnimal(items);
                    animal.setName(animal2.getName());
                    img.setImageBitmap(animal2.getImage());
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option3.getText() == animal.getName()) {
                    stilling++;
                    showPopUpBox("Correct! " + "\nPoints: " + stilling);
                    Animal animal2 = GetRandomAnimal(items);
                    animal.setName(animal2.getName());
                    img.setImageBitmap(animal2.getImage());
                } else {
                    showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                    Animal animal2 = GetRandomAnimal(items);
                    animal.setName(animal2.getName());
                    img.setImageBitmap(animal2.getImage());
                }
            }

        });
    }

    private Animal GetRandomAnimal(List<Animal> items) {
        Random random = new Random();
        if (items.size() > 0) {
            int index = random.nextInt(items.size());
            return items.get(index);
        } else {
            throw new IllegalArgumentException("List must contain at least one item" + items.size());
        }
    }


    private void showPopUpBox(String message) {
       AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id){
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
}