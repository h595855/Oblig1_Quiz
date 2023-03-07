package no.hvl.dat153.Activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.Database.AnimalDatabase;
import no.hvl.dat153.R;

public class MainActivity extends AppCompatActivity {

    //declare
    private Switch mSwitch;
    private Boolean isCheckedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize database
        AnimalDatabase animalDatabase = AnimalDatabase.getDatabase(getApplicationContext());

        // Create ExecutorService with a single background thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Insert animals in the database using executorService
        executorService.execute(() -> {
            Animal a1 = new Animal("Cat", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
            Animal a2 = new Animal("dog", BitmapFactory.decodeResource(this.getResources(), R.drawable.dog));
            Animal a3 = new Animal("among", BitmapFactory.decodeResource(this.getResources(), R.drawable.among));

            animalDatabase.animalDao().insert(a1);
            animalDatabase.animalDao().insert(a2);
            animalDatabase.animalDao().insert(a3);
        });

        // Shutdown the executorService after the database operations are complete
        executorService.shutdown();


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
    }
}