package no.hvl.dat153.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.ByteArrayOutputStream;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.Database.AnimalRepository;
import no.hvl.dat153.R;
import no.hvl.dat153.ViewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    //declare
    private Switch mSwitch;
    private Boolean isCheckedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimalRepository animalRepository = new AnimalRepository(getApplication());

        Button startButton = findViewById(R.id.Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitch = findViewById(R.id.hardMode);
                isCheckedo = mSwitch.isChecked();
                //checking if the switch is put on
                mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        isCheckedo = isChecked;
                    }
                });

                //passing it to game activity
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("timerEnabled", isCheckedo);
                startActivity(intent);
            }
        });

        Button Database = findViewById(R.id.database);
        Database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllAnimals().observe(this,
                animals -> {
                    if(animals.size() < 3){
                        addEntries();
                    }
                }
                );

    }

    MainViewModel mainViewModel;

    public void addEntries(){
        //Added Among
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.among);
        ByteArrayOutputStream Stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, Stream);
        byte[] amongByteArray = Stream.toByteArray();
        mainViewModel.insert(new Animal("among", amongByteArray));

        //Added Among
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        Stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, Stream);
        byte[] catByteArray = Stream.toByteArray();
        mainViewModel.insert(new Animal("cat", catByteArray));

        //Added Among
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        Stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, Stream);
        byte[] dogByteArray = Stream.toByteArray();
        mainViewModel.insert(new Animal("dog", dogByteArray));


    }
}
