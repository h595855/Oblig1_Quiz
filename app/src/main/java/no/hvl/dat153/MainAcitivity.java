package no.hvl.dat153;

import androidx.appcompat.app.AppCompatActivity;
import no.hvl.dat153.MainAcitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAcitivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        Button Database = findViewById(R.id.database);
        Database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAcitivity.this, Databasectivity.class);
                startActivity(intent);
            }
        });
    }
}