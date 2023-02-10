package no.hvl.dat153;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseActivity extends AppCompatActivity {

    Set<Animal> images = new HashSet<Animal>();
    private ListView listView;
    private ListAdapter listAdapter;
    private List<Animal> animalList;

    private ActivityResultLauncher<Intent> AddPictureActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    Animal newAnimal = extras.getParcelable("animal", Animal.class);
                    animalList.add(newAnimal);
                    //updating the list
                    listAdapter = new ListAdapter(this, R.layout.animalitem, animalList);
                    listView.setAdapter(listAdapter);
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasectivity);

        //getting listview
        listView = findViewById(R.id.list_view);
        animalList = new ArrayList<>();

        //creating & adding initial animals to the list
        Animal a1 = new Animal("Cat", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
        Animal a2 = new Animal("dog", BitmapFactory.decodeResource(this.getResources(), R.drawable.dog));
        Animal a3 = new Animal("among", BitmapFactory.decodeResource(this.getResources(), R.drawable.among));

        //adding to list
        animalList.add(a1);
        animalList.add(a2);
        animalList.add(a3);

        //creating listview of images
        listAdapter = new ListAdapter(this, R.layout.animalitem, animalList);
        listView.setAdapter(listAdapter);


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
}