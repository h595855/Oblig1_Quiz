package no.hvl.dat153.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import no.hvl.dat153.Adapters.ListAdapter;
import no.hvl.dat153.Classes.Animal;
import no.hvl.dat153.Database.AnimalRepository;
import no.hvl.dat153.R;

public class DatabaseActivity extends AppCompatActivity {

    Set<Animal> images = new HashSet<Animal>();
    private ListView listView;
    private ListAdapter listAdapter;
    private LiveData<List<Animal>> animalList;
    public AnimalRepository repository;

    private ActivityResultLauncher<Intent> AddPictureActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    Animal newAnimal = extras.getParcelable("animal", Animal.class);
                    repository.insert(newAnimal);
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasectivity);

        repository = new AnimalRepository(this.getApplication());
        animalList = repository.getAllAnimals();

        //getting listview
        listView = findViewById(R.id.list_view);

        //creating listview of images
        listAdapter = new ListAdapter(this, R.layout.animalitem, animalList);
        listView.setAdapter(listAdapter);

        animalList.observe(this, animals -> {
            listAdapter.clear();
            listAdapter.addAll(animals);
            listAdapter.notifyDataSetChanged();
        });

        FloatingActionButton floatAdd = (FloatingActionButton) findViewById(R.id.addPicture);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //making second activity to get update add to list
                Intent intent = new Intent(DatabaseActivity.this, AddPictureActivity.class);
                AddPictureActivity.launch(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        return;
    }
}
