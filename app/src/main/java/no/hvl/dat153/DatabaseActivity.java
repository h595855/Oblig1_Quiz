package no.hvl.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;
    private List<Animal> listItems;

    private Animal animal;

    Set<Animal> images = new HashSet<Animal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasectivity);

        listView = findViewById(R.id.list_view);
        listItems = new ArrayList<>();

        Animal a1 = new Animal("Cat", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
        Animal a2 = new Animal("dog", BitmapFactory.decodeResource(this.getResources(), R.drawable.dog));
        Animal a3 = new Animal("among", BitmapFactory.decodeResource(this.getResources(), R.drawable.among));

        listItems.add(a1);
        listItems.add(a2);
        listItems.add(a3);


        listAdapter = new ListAdapter(this, R.layout.animalitem, listItems);
        listView.setAdapter(listAdapter);

        FloatingActionButton floatAdd = (FloatingActionButton) findViewById(R.id.addPicture);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseActivity.this, AddPictureActivity.class);
                startActivity(intent);
            }
        });
    }
}