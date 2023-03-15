package no.hvl.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import no.hvl.dat153.R;
import no.hvl.dat153.adapters.AnimalListAdapter;
import no.hvl.dat153.data.AnimalViewModel;

public class DatabaseActivity extends AppCompatActivity {

    private AnimalViewModel animalViewModel;
    private RecyclerView recyclerView;
    private AnimalListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasectivity);

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

        FloatingActionButton floatAdd = (FloatingActionButton) findViewById(R.id.addPicture);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //making second activity to get update add to list
                Intent intent = new Intent(DatabaseActivity.this, AddPictureActivity.class);
                //AddPictureActivity.launch(intent);
                startActivity(intent);
            }
        });
    }//onCreate

}//class