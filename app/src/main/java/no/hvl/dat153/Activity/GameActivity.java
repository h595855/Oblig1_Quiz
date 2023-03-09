package no.hvl.dat153.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Random;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.R;
import no.hvl.dat153.ViewModels.QuizViewModel;

public class GameActivity extends AppCompatActivity {

    //define initials
    int stilling = 0;
    private boolean timerEnabled;
    private LiveData<List<Animal>> items;
    private Animal animal;
    private ImageView img;
    private CountDownTimer timer;
    private TextView timerTextView;
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        quizViewModel = new QuizViewModel(getApplication());
        items = quizViewModel.getAllAnimals();

        // spill();

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
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));

                    } else {
                        showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                        Animal animal2 = GetRandomAnimal(items);
                        animal.setName(animal2.getName());
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
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
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
                    } else {
                        showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                        Animal animal2 = GetRandomAnimal(items);
                        animal.setName(animal2.getName());
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
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
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
                    } else {
                        showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                        Animal animal2 = GetRandomAnimal(items);
                        animal.setName(animal2.getName());
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
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
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
                    } else {
                        showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                        Animal animal2 = GetRandomAnimal(items);
                        animal.setName(animal2.getName());
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
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
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
                    } else {
                        showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                        Animal animal2 = GetRandomAnimal(items);
                        animal.setName(animal2.getName());
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
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
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
                    } else {
                        showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                        Animal animal2 = GetRandomAnimal(items);
                        animal.setName(animal2.getName());
                        img.setImageBitmap(byteArrayToBitmap(animal2.getImage()));
                    }
                }

            });
        }

    }

    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private Animal GetRandomAnimal(LiveData<List<Animal>> items) {
        Random random = new Random();
        List<Animal> animalList = items.getValue();
        if (animalList != null && animalList.size() > 0) {
            int index = random.nextInt(animalList.size());
            return animalList.get(index);
        } else {
            throw new IllegalArgumentException("List must contain at least one item" + items.getValue().size());
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