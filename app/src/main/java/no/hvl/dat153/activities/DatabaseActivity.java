package no.hvl.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import no.hvl.dat153.R;
import no.hvl.dat153.adapters.AnimalListAdapter;
import no.hvl.dat153.data.AnimalViewModel;
import no.hvl.dat153.util.SwipeToDeleteCallback;

public class DatabaseActivity extends AppCompatActivity {

    private AnimalViewModel animalViewModel;
    private RecyclerView recyclerView;
    private AnimalListAdapter adapter;

    private ExtendedFloatingActionButton sortAnimalsDatabaseButton;
    private ExtendedFloatingActionButton exitDatabaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasectivity);

        sortAnimalsDatabaseButton = findViewById(R.id.sortAnimalsDatabase);
        sortAnimalsDatabaseButton.setOnClickListener(v -> {
            animalViewModel.getAllAnimals().observe(this, animals -> {
                // Sort the animal list alphabetically by name
                Collections.sort(animals, (a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));

                // Update the adapter with the new animal data
                adapter.setAnimalList(animals);
                adapter.notifyDataSetChanged();
            });
        });

        exitDatabaseButton = findViewById(R.id.exitDatabase);
        exitDatabaseButton.setOnClickListener(v -> {
            onBackPressed();
        });

        //getting listview
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new AnimalListAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //init viewModel
        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        System.err.println(animalViewModel.toString());

        animalViewModel.getAllAnimals().observe(this, animals -> {
            // Update the adapter with the new animal data
            adapter.setAnimalList(animals);
            adapter.notifyDataSetChanged();
        });

        //delete onSwipe helpClassCallback
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter, animalViewModel));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //
        FloatingActionButton addAnimalToDatabaseButton = (FloatingActionButton) findViewById(R.id.addPicture);

        addAnimalToDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //making second activity to get update add to list
                Intent intent = new Intent(DatabaseActivity.this, AddPictureActivity.class);
                //AddPictureActivity.launch(intent);
                startActivity(intent);
            }
        });
    }//onCreate

    @Override
    public void onBackPressed(){
        finish();
    }

}//class