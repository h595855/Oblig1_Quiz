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

/**
 * DatabaseActivity is a class that manages the display and interaction of a
 * list of animals in a database. It allows the user to sort, add, and delete
 * animals from the list.
 */
public class DatabaseActivity extends AppCompatActivity {

    private AnimalViewModel animalViewModel;
    private RecyclerView recyclerView;
    private AnimalListAdapter adapter;

    private ExtendedFloatingActionButton sortAnimalsDatabaseButton;
    private ExtendedFloatingActionButton exitDatabaseButton;

    /**
     * Initializes the view and its components, and sets up the necessary event
     * listeners.
     *
     * @param savedInstanceState a Bundle object containing the activity's
     *                           previously saved state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasectivity);

        // Initialize and set click listener for the sort button
        sortAnimalsDatabaseButton = findViewById(R.id.sortAnimalsDatabase);
        sortAnimalsDatabaseButton.setOnClickListener(v -> {
            // Sort and update the animal list
            animalViewModel.getAllAnimals().observe(this, animals -> {
                Collections.sort(animals, (a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));
                adapter.setAnimalList(animals);
                adapter.notifyDataSetChanged();
            });
        });

        // Initialize and set click listener for the exit button
        exitDatabaseButton = findViewById(R.id.exitDatabase);
        exitDatabaseButton.setOnClickListener(v -> {
            onBackPressed();
        });

        // Initialize the RecyclerView and its adapter
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new AnimalListAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Initialize the AnimalViewModel
        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        System.err.println(animalViewModel);

        // Observe changes in the animal list and update the adapter
        animalViewModel.getAllAnimals().observe(this, animals -> {
            adapter.setAnimalList(animals);
            adapter.notifyDataSetChanged();
        });

        // Initialize swipe-to-delete functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter, animalViewModel));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Initialize the add animal button and set its click listener
        FloatingActionButton addAnimalToDatabaseButton = (FloatingActionButton) findViewById(R.id.addPicture);
        addAnimalToDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AddPictureActivity to add a new animal
                Intent intent = new Intent(DatabaseActivity.this, AddPictureActivity.class);
                startActivity(intent);
            }
        });
    }//onCreate

    /**
     * Called when the activity has detected the user's press of the back key.
     * Finishes the activity and returns to the previous one.
     */
    @Override
    public void onBackPressed() {
        finish();
    }

}//class
