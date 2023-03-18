package no.hvl.dat153.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.R;

public class GameActivity extends AppCompatActivity {

    //define initials
    int stilling = 0;
    private boolean timerEnabled;
    private ArrayList<Animal> items;
    private Animal animal;
    private ImageView img;
    private CountDownTimer timer;
    private TextView timerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Animal a1 = new Animal("Cat", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
        Animal a2 = new Animal("dog", BitmapFactory.decodeResource(this.getResources(), R.drawable.dog));
        Animal a3 = new Animal("among", BitmapFactory.decodeResource(this.getResources(), R.drawable.among));

        items = new ArrayList<Animal>();

        items.add(a1);
        items.add(a2);
        items.add(a3);

        spill();

    }
    //fixes the part when user exits activity timer is canceled
    @Override
    protected void onPause() {
        //super has to be used or exception is thrown and check for timer is not null
        super.onPause();
        if(timer != null){
            super.onPause();
            timer.cancel();
            timer = null;
        }
    }

    private void spill() {

        animal = GetRandomAnimal(items);

        img = findViewById(R.id.image);

        //Set random image from random chosen animal
        img.setImageBitmap(animal.getImage());

        Button option1 = findViewById(R.id.alt1);
        Button option2 = findViewById(R.id.alt2);
        Button option3 = findViewById(R.id.alt3);

        option1.setText("Cat");
        option2.setText("dog");
        option3.setText("among");

        Intent intent = getIntent();
        timerEnabled = intent.getBooleanExtra("timerEnabled", false);
        //checking if the switch worked
        System.out.println(timerEnabled);

        if (timerEnabled) {
            startTimer();
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

                    //resetTimer();
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

                   // resetTimer();
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

                  //  resetTimer();
                }


            });
            showTimer();

        } else {
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
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //method that resets current timer
    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        startTimer();
    }

    //creates & starts a 5 sec timer with wrong function onFinish
    private void startTimer() {
        //every second telling you how much time is left
        timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            //when timer runs out it runs wrong answer & generates new Animal
            public void onFinish() {
                showPopUpBox("Time is up. Right answer: \n" + animal.getName());
                onBackPressed();
               // Animal animal2 = GetRandomAnimal(items);
               // animal.setName(animal2.getName());
               // img.setImageBitmap(animal2.getImage());
               // startTimer();
            }
        }.start();
    }
    private void showTimer(){
        timerTextView = (TextView) findViewById(R.id.timer_text_view);
        startTimer();
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this,"Final score is " + stilling, Toast.LENGTH_SHORT).show();
        finish();
        return;
     }
}