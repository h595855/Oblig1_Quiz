package no.hvl.dat153.activities;

import android.content.DialogInterface;
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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.hvl.dat153.R;
import no.hvl.dat153.data.Animal;
import no.hvl.dat153.data.AnimalViewModel;

public class GameActivity extends AppCompatActivity {

    //define initials
    int stilling = 0;
    private Animal animal;
    private ImageView img;
    private CountDownTimer timer;
    private boolean timerEnabled;
    private TextView timerTextView;
    private List<Animal> animalList;
    private AnimalViewModel animalViewModel;

    private Button buttonOption1;
    private Button buttonOption2;
    private Button buttonOption3;

    private ExtendedFloatingActionButton exitGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        exitGameButton = findViewById(R.id.exitGame);
        exitGameButton.setOnClickListener(v -> {
            onBackPressed();
        });

        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);

        animalViewModel.getAllAnimals().observe(this, animals -> {
            animalList = animals;
            spill();
        });

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

        timerEnabled = getIntent().getBooleanExtra("timerEnabled", false);

        animal = GetRandomAnimal(animalList);

        img = findViewById(R.id.image);

        buttonOption1 = findViewById(R.id.alt1);
        buttonOption2 = findViewById(R.id.alt2);
        buttonOption3 = findViewById(R.id.alt3);

        newQuestion();
        if(timerEnabled){
            timerTextView = findViewById(R.id.secondsRemaining);
            startTimer();
        }

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (button.getText().equals(animal.getName())) {
                    stilling++;
                    showPopUpBox("Correct! " + "\nPoints: " + stilling);
                } else {
                    showPopUpBox("Wrong. Right answer was: " + animal.getName() + "\nPoints: " + stilling);
                }
                newQuestion();

                if (timerEnabled) {
                    resetTimer();
                }
            }
        };

        buttonOption1.setOnClickListener(buttonClickListener);
        buttonOption2.setOnClickListener(buttonClickListener);
        buttonOption3.setOnClickListener(buttonClickListener);

    }//spill

    private Animal GetRandomAnimal(List<Animal> items) {
        Random random = new Random();
        if (items.size() > 0) {
            int index = random.nextInt(items.size());
            return items.get(index);
        } else {
            throw new IllegalArgumentException("List must contain at least one item" + items.size());
        }
    }

    private List<Animal> getRandomAnimals(List<Animal> items) {
        if (items.size() < 3) {
            throw new IllegalArgumentException("List must contain at least three items");
        }

        List<Animal> randomAnimals = new ArrayList<>();
        Random random = new Random();

        while (randomAnimals.size() < 3) {
            int index = random.nextInt(items.size());
            Animal animal = items.get(index);
            if (!randomAnimals.contains(animal)) {
                randomAnimals.add(animal);
            }
        }

        return randomAnimals;
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

    private void newQuestion (){
        List<Animal> randomAnimals = getRandomAnimals(animalList);
        buttonOption1.setText(randomAnimals.get(0).getName());
        buttonOption2.setText(randomAnimals.get(1).getName());
        buttonOption3.setText(randomAnimals.get(2).getName());
        Animal randomAnimal = GetRandomAnimal(randomAnimals);

        animal = randomAnimal;
        byte[] imageByteArray = animal.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        img.setImageBitmap(bitmap);
    }

    //creates & starts a 5 sec timer with wrong function onFinish
    private void startTimer() {
        //every second telling you how much time is left
        timer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            //when timer runs out it runs wrong answer & generates new Animal
            public void onFinish() {
                showPopUpBox("Wrong :/");
                newQuestion ();
                startTimer();
            }
        }.start();
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this,"Final score is " + stilling, Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

}